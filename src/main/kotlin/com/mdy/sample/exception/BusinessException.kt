package com.mdy.sample.exception

data class BusinessException(
    val code: ExceptionCode,
    override val message: String,
) : RuntimeException()