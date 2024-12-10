package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenExpiredException : BusinessException(JwtTokenError.JWT_EXPIRED) {

    private fun readResolve(): Any = TokenExpiredException

    val EXCEPTION: TokenExpiredException = TokenExpiredException

}