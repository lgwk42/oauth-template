package com.project.oauthtemplate.global.common.dto.request

data class PageRequest(
    var page: Long = 1,
    var size: Long = 15,
)