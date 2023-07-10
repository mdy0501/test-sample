# About SpringBoot Exception Handling

# 실습1
- 컨트롤러 내에서만 실행하는 실습

## `@ExceptionHandler`
- ExceptionCode.java
  ```kotlin 
  enum class ExceptionCode(
      val code: Int,
      val message: String,
  ) {
      INVALID_TOKEN(401, "Invalid Token"),
      UNAUTHORIZED(401, "Unauthorized"),
      FORBIDDEN(403, "Forbidden"),
      MEMBER_NOT_FOUND(404, "Member Not Found"),
      COMMU_NOT_FOUND(404, "Content Not Found"),
      COMMENT_NOT_FOUND(404, "Comment Not Found"),
      METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
      TIME_OUT(408, "Time-out"),
      MEMBER_EXISTS(409, "Member Exists"),
      NOT_FOUND(500, "The Requested Access Is Invalid."),
      NOT_IMPLEMENTATION(501, "Not Implementation"),
      BAD_GATEWAY(502, "Bad Gateway"),
      SERVICE_UNAVAILABLE(503, "Service Temporarily Unavailable"),
      GATEWAY_TIMEOUT(504, "Gateway Time-out"),
      UNKNOWN_ERROR(520, "Unknown Error");
  }
  ```

### case1 호출
- 컨트롤러 내부에 있는 아래 함수가 캐치
```kotlin
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
```

### case2 호출



<br><br><br>


# 실습2
- 컨트롤러 내부가 아닌 전역으로 Exception을 핸들링하기

## @RestControllerAdvice / @ExceptionHandler

### case1 호출
- 기존에 컨트롤러에 있는 @ExceptionHandler 주석 처리(컨트롤러에 있는 handleCases() 메서드)하고, 전역적으로 처리하는 실습하기


### case2 호출
- 예상하지 못한 exception은 Exception으로 받아서 처리하기
  - 알림을 받아볼 수 있도록 셋팅하고, 예외의  케이스에 따라 점차적으로 예외를 관리



<br><br><br>


# 실습3
- BusinessException을 ExceptionCode에 따라 HttpException으로 맵핑하기

## ExceptionControllerAdvice2 / ExceptionConverter

### 예시
- ExceptionControllerAdvice2
```kotlin
package com.mdy.sample.exception

import com.mdy.sample.exception.http.ExceptionConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice2(
    private val exceptionConverter: ExceptionConverter
) {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(exception: Exception) {
        println("### [ExceptionControllerAdvice2] call handleException()")
        println("### exception: ${exception}")
        println("### exception message: ${exception.message}")
    }

    @ExceptionHandler(value = [BusinessException::class])
    fun handleBusinessException(exception: BusinessException) {
        println("### [ExceptionControllerAdvice2] call handleBusinessException()")
        println("### exception message: ${exception.message}")

        exceptionConverter.convertToHttpException(exception.code)
    }
}
```

- ExceptionConverter
```kotlin
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
```


### case1 
- 컨트롤러 ExceptionHandler, ExceptionControllerAdvice 주석 걸고, 스웨거로 테스트해보기

