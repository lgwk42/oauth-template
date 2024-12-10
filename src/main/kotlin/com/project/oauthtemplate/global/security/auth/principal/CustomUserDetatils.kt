package com.project.oauthtemplate.global.security.auth.principal

import com.project.oauthtemplate.domain.user.dto.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails: UserDetails {

    private val user: User
    private val authorities: Collection<GrantedAuthority>

    fun getUser(): User {
        return this.user
    }

    constructor(user: User, authorities: Collection<GrantedAuthority>) {
        this.user = user
        this.authorities = authorities
    }

    constructor(user: User) {
        this.user = user
        this.authorities = setOf(GrantedAuthority {
            user.userRole.key
        })
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return setOf(GrantedAuthority { user.userRole.key })
//                return authorities;
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun create(user: User): CustomUserDetails {
            return CustomUserDetails(user, setOf(user.userRole::key as GrantedAuthority))
        }
    }

}