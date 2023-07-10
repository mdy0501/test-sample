//package com.mdy.sample.exception
//
//import com.mdy.sample.exception.http.ExceptionConverter
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.RestControllerAdvice
//
//@RestControllerAdvice
//class ExceptionControllerAdvice2(
//    private val exceptionConverter: ExceptionConverter
//) {
//
//    @ExceptionHandler(value = [Exception::class])
//    fun handleException(exception: Exception) {
//        println("### [ExceptionControllerAdvice2] call handleException()")
//        println("### exception: $exception")
//        println("### exception message: ${exception.message}")
//    }
//
//    @ExceptionHandler(value = [BusinessException::class])
//    fun handleBusinessException(exception: BusinessException) {
//        println("### [ExceptionControllerAdvice2] call handleBusinessException()")
//        println("### exception: $exception")
//        println("### exception message: ${exception.message}")
//
//        exceptionConverter.convertToHttpException(exception.code)
//    }
//}