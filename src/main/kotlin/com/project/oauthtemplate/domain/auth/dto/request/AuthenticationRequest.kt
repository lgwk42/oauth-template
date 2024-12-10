package com.project.oauthtemplate.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(

    @NotBlank
    val idToken: String,

)
