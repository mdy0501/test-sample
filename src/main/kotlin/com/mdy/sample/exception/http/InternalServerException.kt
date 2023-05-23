package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus

class InternalServerException(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : HttpException(HttpStatus.INTERNAL_SERVER_ERROR, code, message, cause)
