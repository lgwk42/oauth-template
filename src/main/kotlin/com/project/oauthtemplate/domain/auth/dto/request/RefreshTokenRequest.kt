package com.project.oauthtemplate.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @NotBlank
    val refreshToken: String
)
