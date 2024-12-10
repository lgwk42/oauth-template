package com.project.oauthtemplate.global.exception

import com.project.oauthtemplate.global.exception.error.ErrorProperty
import lombok.RequiredArgsConstructor

open class BusinessException(val error: ErrorProperty) : RuntimeException() {

}