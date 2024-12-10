package com.project.oauthtemplate.global.security.jwt.filter

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import kotlin.coroutines.CoroutineContext

data class SecurityContextElement(val context: SecurityContext) : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<SecurityContextElement>

    override val key: CoroutineContext.Key<*> = Key
}

fun CoroutineContext.getSecurityContext(): SecurityContext? =
    (this[SecurityContextElement] as? SecurityContextElement)?.context

fun withSecurityContextContext(context: CoroutineContext): CoroutineContext {
    val securityContext = SecurityContextHolder.getContext()
    return context + SecurityContextElement(securityContext)
}