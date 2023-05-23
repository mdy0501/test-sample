//package com.mdy.sample.exception
//
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.RestControllerAdvice
//
//@RestControllerAdvice
//class ExceptionControllerAdvice {
//
//    @ExceptionHandler(value = [Exception::class])
//    fun handleException(exception: Exception) {
//        println("### [ExceptionControllerAdvice] call handleException()")
//        println("### exception: ${exception}")
//        println("### exception message: ${exception.message}")
//
//
//    }
//
//    @ExceptionHandler(value = [BusinessException::class])
//    fun handleBusinessException(exception: BusinessException) {
//        println("### [ExceptionControllerAdvice] call handleBusinessException()")
//        println("### exception message: ${exception.message}")
//
//        when(exception.code) {
//            ExceptionCode.INVALID_TOKEN -> println("### Handle INVALID_TOKEN exception!")
//            ExceptionCode.UNAUTHORIZED -> println("### Handle UNAUTHORIZED exception!")
//            else -> println("### Handle other exception!")
//        }
//    }
//}