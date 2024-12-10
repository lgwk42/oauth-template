package com.project.oauthtemplate.global.security.jwt.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.oauthtemplate.global.common.dto.response.BaseResponse
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtErrorType
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtTokenError
import com.project.oauthtemplate.global.security.jwt.JwtExtract
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val extract: JwtExtract,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader("Authorization")

        if (token.isNullOrEmpty() || !token.startsWith("Bearer ")) {
            doFilter(request, response, filterChain)
//            setErrorResponse(response, JwtTokenError.JWT_EMPTY_EXCEPTION)
        } else {
            when (extract.checkTokenInfo(extract.getToken(token))) {
                JwtErrorType.OK -> {
                    SecurityContextHolder.getContext().authentication = extract.getAuthentication(token)
                    doFilter(request, response, filterChain)
                }

                JwtErrorType.ExpiredJwtException -> setErrorResponse(response, JwtTokenError.JWT_EXPIRED)
                JwtErrorType.SignatureException -> setErrorResponse(response, JwtTokenError.JWT_TOKEN_SIGNATURE_ERROR)
                JwtErrorType.MalformedJwtException -> setErrorResponse(response, JwtTokenError.JWT_TOKEN_ERROR)
                JwtErrorType.UnsupportedJwtException -> setErrorResponse(
                    response,
                    JwtTokenError.JWT_NOT_SUPPORT
                )

                JwtErrorType.IllegalArgumentException -> setErrorResponse(
                    response,
                    JwtTokenError.JWT_ERROR
                )

                JwtErrorType.UNKNOWN_EXCEPTION -> setErrorResponse(response, JwtTokenError.JWT_UNKNOWN_EXCEPTION)
            }
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        errorCode: JwtTokenError
    ) {
        response.status = errorCode.status.value()
        response.contentType = "application/json;charset=UTF-8"

        response.writer.write(
            objectMapper.writeValueAsString(
                BaseResponse.of(
                    status = errorCode.status,
                    message = errorCode.message
                )
            )
        )
    }

}