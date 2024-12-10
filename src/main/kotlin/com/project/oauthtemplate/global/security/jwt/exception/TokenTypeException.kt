package com.project.oauthtemplate.global.security.jwt.exception

import com.project.oauthtemplate.global.exception.BusinessException
import com.project.oauthtemplate.global.security.jwt.exception.error.JwtTokenError

object TokenTypeException : BusinessException(JwtTokenError.JWT_TOKEN_ERROR) {

    private fun readResolve(): Any = TokenTypeException

    val EXCEPTION: TokenTypeException = TokenTypeException

}