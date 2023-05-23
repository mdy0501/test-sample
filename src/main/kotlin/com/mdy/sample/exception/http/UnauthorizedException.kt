package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus

class UnauthorizedException(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : HttpException(HttpStatus.UNAUTHORIZED, code, message, cause)
