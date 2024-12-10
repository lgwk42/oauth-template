package com.project.oauthtemplate.global.security.jwt.exception.error

import com.project.oauthtemplate.global.exception.error.ErrorProperty
import org.springframework.http.HttpStatus

enum class JwtTokenError(
    override val status: HttpStatus, override val message: String
) : ErrorProperty {

    JWT_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "잘못된 타입"),
    JWT_EXPIRED(HttpStatus.GONE, "만료된 토큰"),
    JWT_ERROR(HttpStatus.BAD_REQUEST, "잘못된 토큰"),
    JWT_TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED ,"토큰의 서명이 일치하지 않음"),
    JWT_UNKNOWN_EXCEPTION(HttpStatus.UNAUTHORIZED,  "JWT 처리과정에서 알수없는 오류 발생"),
    JWT_EMPTY_EXCEPTION(HttpStatus.FORBIDDEN,  "토큰을 넣어주세요."),
    JWT_NOT_SUPPORT(HttpStatus.BAD_REQUEST, "지원하지 않는 토큰");

}