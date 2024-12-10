package com.project.oauthtemplate.global.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.oauthtemplate.global.security.jwt.JwtExtract
import com.project.oauthtemplate.global.security.jwt.filter.JwtAuthenticationFilter
import com.project.oauthtemplate.global.security.jwt.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val objectMapper: ObjectMapper,
    private val jwtEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtExtract: JwtExtract,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity
    ): SecurityFilterChain {

        SecurityContextHolder.setStrategyName(MODE_INHERITABLETHREADLOCAL)

        return http
            .csrf { it.disable() }
            .cors { corsConfigurationSource() }
            .formLogin { it.disable() }
            .securityContext { it.requireExplicitSave(true) }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { exceptionHandlingConfigurer: ExceptionHandlingConfigurer<HttpSecurity> ->
                exceptionHandlingConfigurer.authenticationEntryPoint(jwtEntryPoint)}
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(objectMapper, jwtExtract), UsernamePasswordAuthenticationFilter::class.java)

//            .exceptionHandling {
//                it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.NOT_FOUND))
//            }

            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOriginPattern("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true

        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return urlBasedCorsConfigurationSource
    }



}