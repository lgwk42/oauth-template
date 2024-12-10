package com.project.oauthtemplate.global.infra.google.service

import com.project.oauthtemplate.global.infra.google.config.GoogleProperties
import com.project.oauthtemplate.global.infra.google.dto.OAuth2Attribute
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Service
class GoogleService(
    private val googleProperties: GoogleProperties,
    private val restTemplate: RestTemplate
) {

    fun getTokenInfo(idToken: String): OAuth2Attribute {
        val googleTokenInfo = restTemplate.getForObject(
            uriComponentGoogleTokenInfo(idToken),
            OAuth2Attribute::class.java
        ) ?: throw IllegalArgumentException("토큰을 찾을 수 없습니다.")
        return googleTokenInfo
    }

    private fun uriComponentGoogleTokenInfo(idToken: String): URI {
        return UriComponentsBuilder.fromUriString(googleProperties.tokenUri)
            .queryParam("id_token", idToken)
            .queryParam("Type", "json")
            .encode()
            .build().toUri()
    }
}