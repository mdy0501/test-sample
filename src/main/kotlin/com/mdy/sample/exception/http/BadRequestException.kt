package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus

class BadRequestException(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : HttpException(HttpStatus.BAD_REQUEST, code, message, cause)
