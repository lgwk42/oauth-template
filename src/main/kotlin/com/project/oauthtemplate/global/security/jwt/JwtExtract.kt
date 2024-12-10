package com.project.oauthtemplate.global.security.jwt

import com.project.oauthtemplate.domain.user.domain.repository.jpa.UserJpaRepository
import com.project.oauthtemplate.domain.user.dto.User
import com.project.oauthtemplate.domain.user.exception.UserNotFoundException
import com.project.oauthtemplate.global.security.auth.principal.CustomUserDetails
import com.project.oauthtemplate.global.security.jwt.config.JwtProperties
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtErrorType
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtExtract(
    private val jwtProperties: JwtProperties,
    private val userJpaRepository: UserJpaRepository
) {

    private val secretKey: SecretKey = SecretKeySpec(
        this.jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8),
        Jwts.SIG.HS256.key().build().algorithm
    )

    fun checkTokenInfo(token: String): JwtErrorType {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
            return JwtErrorType.OK
        } catch (e: ExpiredJwtException) {
            return JwtErrorType.ExpiredJwtException
        } catch (e: java.security.SignatureException) {
            return JwtErrorType.SignatureException
        } catch (e: MalformedJwtException) {
            return JwtErrorType.MalformedJwtException
        } catch (e: UnsupportedJwtException) {
            return JwtErrorType.UnsupportedJwtException
        } catch (e: IllegalArgumentException) {
            return JwtErrorType.IllegalArgumentException
        } catch (e: Exception) {
            return JwtErrorType.UNKNOWN_EXCEPTION
        }
    }

    fun getAuthentication(token: String): Authentication {
        val t = getToken(token)
        val user = findUserByEmail(getUsername(t))

        val details = CustomUserDetails(user)

        return UsernamePasswordAuthenticationToken(details, null, details.getAuthorities())
    }

    fun getToken(token: String): String {
        return token.removePrefix("Bearer ")
    }

    fun getUsername(token: String): String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get(
            "email",
            String::class.java
        )
    }

    fun findUserByEmail(email: String): User {
        return userJpaRepository.findByEmail(email)
            .map { userEntity -> User.toUser(userEntity) }
            .orElseThrow { UserNotFoundException }
    }

}