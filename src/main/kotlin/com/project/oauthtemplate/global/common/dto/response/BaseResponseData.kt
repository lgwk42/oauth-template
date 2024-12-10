package com.project.oauthtemplate.global.common.dto.response

import org.springframework.http.HttpStatus

data class BaseResponseData<T>(
    val status: Int,
    val message: String,
    val data: T
) {
    companion object {
        fun <T> ok(data: T, message: String) =
            BaseResponseData(HttpStatus.OK.value(), message, data)

        fun <T> of(status: HttpStatus, message: String, data: T) =
            BaseResponseData(status.value(), message, data)
    }
}

