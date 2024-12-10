package com.project.oauthtemplate.domain.user.exception

import com.project.oauthtemplate.domain.user.exception.error.UserError
import com.project.oauthtemplate.global.exception.BusinessException

object UserNotFoundException : BusinessException(UserError.USER_NOT_FOUND) {

    private fun readResolve(): Any = UserNotFoundException

    val EXCEPTION: UserNotFoundException = UserNotFoundException

}