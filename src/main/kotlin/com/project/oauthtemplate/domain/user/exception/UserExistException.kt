package com.project.oauthtemplate.domain.user.exception

import com.project.oauthtemplate.domain.user.exception.error.UserError
import com.project.oauthtemplate.global.exception.BusinessException

object UserExistException : BusinessException(UserError.USER_EXIST) {

    private fun readResolve(): Any = UserExistException

    val EXCEPTION: UserExistException = UserExistException

}