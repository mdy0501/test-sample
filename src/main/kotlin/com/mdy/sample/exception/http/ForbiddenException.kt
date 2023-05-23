package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus

class ForbiddenException(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : HttpException(HttpStatus.FORBIDDEN, code, message, cause)
