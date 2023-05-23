package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus

class NotFoundException(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : HttpException(HttpStatus.NOT_FOUND, code, message, cause)
