package com.mdy.sample.controller

import com.mdy.sample.exception.BusinessException
import com.mdy.sample.exception.ExceptionCode
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/exception-handle-test")
@RestController
class ExceptionHandlerTestController {

    @ApiOperation(value = "[CASE: INVALID_TOKEN]")
    @GetMapping("/exception-case1")
    fun exceptionCase1() {
        println("### call test1")

        throw BusinessException(code = ExceptionCode.INVALID_TOKEN, "msg1")
    }

    @ApiOperation(value = "[CASE: UNAUTHORIZED]")
    @GetMapping("/exception-case2")
    fun exceptionCase2() {
        println("### call test2")

        throw BusinessException(code = ExceptionCode.UNAUTHORIZED, "msg2")
    }

    @ApiOperation(value = "[CASE: FORBIDDEN]")
    @GetMapping("/exception-case3")
    fun exceptionCase3() {
        println("### call test3")

        throw BusinessException(code = ExceptionCode.FORBIDDEN, "msg3")
    }

    @ApiOperation(value = "[CASE: SERVICE_UNAVAILABLE]")
    @GetMapping("/exception-case4")
    fun exceptionCase4() {
        println("### call test4")

        throw BusinessException(code = ExceptionCode.SERVICE_UNAVAILABLE, "msg4")
    }

    @ApiOperation(value = "[CASE: 예측하지 못하는 에러들]")
    @GetMapping("/exception-case99")
    fun exceptionCase99() {
        println("### call test99")

//        throw BusinessException(code = ExceptionCode.UNAUTHORIZED, "msg2")
        val array = arrayOf(1, 2, 3, 4, 5)
        println(array[8])
        // ### exception: java.lang.ArrayIndexOutOfBoundsException: Index 8 out of bounds for length 5
        // ### exception message: Index 8 out of bounds for length 5
//        throw ArrayIndexOutOfBoundsException()
    }


    @ExceptionHandler(value = [BusinessException::class])
    fun handleCases(exception: BusinessException) {
        println("### call handle case1")
        println("### exception message: ${exception.message}")

        when(exception.code) {
            ExceptionCode.INVALID_TOKEN -> println("### Handle INVALID_TOKEN exception!")
            ExceptionCode.UNAUTHORIZED -> println("### Handle UNAUTHORIZED exception!")
            else -> println("# Handle other exception!")
        }
    }
}