package com.project.oauthtemplate.global.common.repository

import com.project.oauthtemplate.domain.user.dto.User
import com.project.oauthtemplate.global.security.auth.principal.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Repository

@Repository
class UserSecurityImpl : UserSecurity {

    override fun getUser(): User {
        val user: User = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUserDetails).getUser()
        return user
    }

}