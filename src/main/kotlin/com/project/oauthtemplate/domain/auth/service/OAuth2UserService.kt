package com.project.oauthtemplate.domain.auth.service

import com.project.oauthtemplate.domain.auth.dto.request.AuthenticationRequest
import com.project.oauthtemplate.domain.auth.dto.request.RefreshTokenRequest
import com.project.oauthtemplate.domain.auth.dto.response.JsonWebTokenResponse
import com.project.oauthtemplate.domain.auth.dto.response.RefreshTokenResponse
import com.project.oauthtemplate.domain.user.domain.enum.UserRole
import com.project.oauthtemplate.domain.user.domain.repository.jpa.UserJpaRepository
import com.project.oauthtemplate.domain.user.dto.User
import com.project.oauthtemplate.domain.user.exception.UserNotFoundException
import com.project.oauthtemplate.global.infra.google.dto.OAuth2Attribute
import com.project.oauthtemplate.global.infra.google.service.GoogleService
import com.project.oauthtemplate.global.security.jwt.JwtExtract
import com.project.oauthtemplate.global.security.jwt.JwtProvider
import com.project.oauthtemplate.global.security.jwt.exception.TokenExpiredException
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtErrorType
import io.jsonwebtoken.lang.Strings
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OAuth2UserService(
    private val userJpaRepository: UserJpaRepository,
    private val encoder: PasswordEncoder,
    private val googleService: GoogleService,
    private val jwtExtract: JwtExtract,
    private val jwtProvider: JwtProvider
) {

    fun auth(request: AuthenticationRequest): JsonWebTokenResponse {
        val oAuth2Attribute: OAuth2Attribute = googleService.getTokenInfo(request.idToken)
        if (!Strings.hasText(oAuth2Attribute.email)) {
            throw UserNotFoundException.EXCEPTION
        }
        val user = findOrSave(oAuth2Attribute)
        return JsonWebTokenResponse(
            jwtProvider.generateAccessToken(user.email, user.userRole),
            jwtProvider.generateRefreshToken(user.email, user.userRole)
        )
    }

    fun refresh(request: RefreshTokenRequest): RefreshTokenResponse {
        val got = jwtExtract.getToken(request.refreshToken)
        val user = jwtExtract.findUserByEmail(got)
        if (jwtExtract.checkTokenInfo(got) == JwtErrorType.ExpiredJwtException) {
            throw TokenExpiredException.EXCEPTION
        }
        return RefreshTokenResponse(
            jwtProvider.generateAccessToken(user.email, user.userRole),
        )
    }

    private fun findOrSave(oAuth2Attribute: OAuth2Attribute): User {
        val user: User = userJpaRepository.findByEmail(oAuth2Attribute.email)
                .map { userEntity -> User.toUser(userEntity) }
                .orElseThrow { UserNotFoundException }
        return user
    }

    fun save(request: User){
        userJpaRepository.save(User.toEntity(User(
            email = request.email,
            name = request.name,
            password = encoder.encode(request.password),
            userRole = UserRole.USER,
        )))
    }
}