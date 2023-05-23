package com.mdy.sample.exception.http

import com.mdy.sample.exception.ExceptionCode
import com.mdy.sample.exception.ExceptionCode.BAD_GATEWAY
import com.mdy.sample.exception.ExceptionCode.FORBIDDEN
import com.mdy.sample.exception.ExceptionCode.GATEWAY_TIMEOUT
import com.mdy.sample.exception.ExceptionCode.INVALID_TOKEN
import com.mdy.sample.exception.ExceptionCode.NOT_FOUND
import com.mdy.sample.exception.ExceptionCode.NOT_IMPLEMENTATION
import com.mdy.sample.exception.ExceptionCode.SERVICE_UNAVAILABLE
import com.mdy.sample.exception.ExceptionCode.UNAUTHORIZED
import com.mdy.sample.exception.ExceptionCode.UNKNOWN_ERROR
import org.springframework.stereotype.Component

@Component
class ExceptionConverter {

    fun convertToHttpException(exceptionCode: ExceptionCode) {
        when (exceptionCode) {
            INVALID_TOKEN, UNAUTHORIZED -> throw UnauthorizedException(exceptionCode.code, exceptionCode.message)
            NOT_FOUND -> throw NotFoundException(code = exceptionCode.code, message = exceptionCode.message)
            FORBIDDEN -> throw ForbiddenException(code = exceptionCode.code, message = exceptionCode.message)
            NOT_IMPLEMENTATION, BAD_GATEWAY, SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT, UNKNOWN_ERROR -> throw InternalServerException(
                code = exceptionCode.code,
                message = exceptionCode.message
            )
        }
    }
}