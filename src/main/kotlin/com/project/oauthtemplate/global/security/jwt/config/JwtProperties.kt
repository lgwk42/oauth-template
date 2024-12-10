package com.project.oauthtemplate.global.security.jwt.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class JwtProperties(

    @Value("\${application.jwt.secretKey}") val secretKey: String,
    @Value("\${application.jwt.expiration}") val expiration: Long,
    @Value("\${application.jwt.refreshExpiration}") val refreshExpiration: Long

)