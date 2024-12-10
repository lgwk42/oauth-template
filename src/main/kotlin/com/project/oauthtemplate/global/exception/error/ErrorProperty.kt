package com.project.oauthtemplate.global.exception.error

import org.springframework.http.HttpStatus

interface ErrorProperty {

    val status: HttpStatus
    val message: String

}