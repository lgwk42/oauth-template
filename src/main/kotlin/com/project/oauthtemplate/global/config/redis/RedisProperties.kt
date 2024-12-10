package com.project.oauthtemplate.global.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class RedisProperties(
    @Value("\${spring.redis.host}") val host: String,
    @Value("\${spring.redis.port}") val port: Int
)