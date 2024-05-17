package com.mdy.sample.controller

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@RequestMapping("/api/validation-test")
@RestController
class ValidationTestController {


    @PutMapping("/put")
    fun test1(
        @RequestBody @Valid req: TestRequest
    ): Response {
        println("### req: $req")

        return object : Response {
            val name = req.name
            val bizNo = req.bizNo
        }
    }

    interface Response

    data class TestRequest(
        val name: String,
        @field:Pattern(regexp = "^[0-9]*$", message = "사업자번호는 숫자만 가능합니다.")
        @field:Size(min = 10, max = 10, message = "사업자번호는 10자리 입니다.")
//        @field:NotNull(message = "사업자번호는 필수입니다.")
        @field:NotBlank(message = "사업자번호는 필수입니다.")
        val bizNo: String? = null,
    )
}