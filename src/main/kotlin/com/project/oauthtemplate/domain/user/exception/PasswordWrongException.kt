package com.project.oauthtemplate.domain.user.exception

import com.project.oauthtemplate.domain.user.exception.error.UserError
import com.project.oauthtemplate.global.exception.BusinessException

object PasswordWrongException : BusinessException(UserError.PASSWORD_WRONG) {

    private fun readResolve(): Any = PasswordWrongException

    val EXCEPTION: PasswordWrongException = PasswordWrongException

}