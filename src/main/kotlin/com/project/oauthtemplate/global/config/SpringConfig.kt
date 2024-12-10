package com.project.oauthtemplate.global.config

import org.springframework.boot.autoconfigure.web.embedded.TomcatVirtualThreadsWebServerFactoryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.client.RestTemplate

@Configuration
class SpringConfig {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun tomcatVirtualThreadsWebServerFactoryCustomizer(): TomcatVirtualThreadsWebServerFactoryCustomizer {
        return TomcatVirtualThreadsWebServerFactoryCustomizer()
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}