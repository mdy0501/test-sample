package com.mdy.sample.exception.http

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

abstract class HttpException(
    status: HttpStatus,
    val code: Int,
    message: String,
    cause: Throwable? = null,
) : ResponseStatusException(status, message, cause) {

    val log: String
        get() = "${this.message}(${this.code})"
}
