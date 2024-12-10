package com.project.oauthtemplate.global.common.repository

import com.project.oauthtemplate.domain.user.dto.User

interface UserSecurity {

    fun getUser(): User

}