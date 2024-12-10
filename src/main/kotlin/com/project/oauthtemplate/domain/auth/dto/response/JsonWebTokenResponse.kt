package com.project.oauthtemplate.domain.auth.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class JsonWebTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
