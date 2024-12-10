package com.project.oauthtemplate.global.common.dto.response

import org.springframework.http.HttpStatus

data class BaseResponse(val value: Int, val message: String) {

    companion object {

        fun ok(message: String): BaseResponse {
            return BaseResponse(HttpStatus.OK.value(), message)
        }

        fun created(message: String): BaseResponse {
            return BaseResponse(HttpStatus.CREATED.value(), message)
        }

        fun of(status: HttpStatus, message: String): BaseResponse {
            return BaseResponse(status.value(), message)
        }
    }

}