package com.project.oauthtemplate.global.infra.google.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class GoogleProperties(
    @Value("\${spring.security.oauth2.client.provider.google}") val tokenInfo: String
)
