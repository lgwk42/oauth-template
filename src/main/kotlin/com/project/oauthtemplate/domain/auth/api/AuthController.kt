package com.project.oauthtemplate.domain.auth.api

import com.project.oauthtemplate.domain.auth.dto.request.AuthenticationRequest
import com.project.oauthtemplate.domain.auth.dto.request.RefreshTokenRequest
import com.project.oauthtemplate.domain.auth.dto.response.JsonWebTokenResponse
import com.project.oauthtemplate.domain.auth.dto.response.RefreshTokenResponse
import com.project.oauthtemplate.domain.auth.service.OAuth2UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val oAuth2UserService: OAuth2UserService
) {

    @PostMapping("")
    fun auth(@Valid @RequestBody authRequest: AuthenticationRequest): JsonWebTokenResponse {
        return oAuth2UserService.auth(authRequest)
    }

    @PostMapping("/refresh")
    fun refresh(@Valid @RequestBody refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse {
        return oAuth2UserService.refresh(refreshTokenRequest)
    }
}