package com.mdy.sample.controller

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME
import com.fasterxml.jackson.annotation.JsonTypeName
import com.mdy.sample.utils.logger
import io.swagger.annotations.ApiModelProperty
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RequestMapping("/api/json-type-info")
@RestController
class JsonTypeInfoController {

    @PostMapping
    @Operation(summary = "Audit 단건 조회", description = "Audit 단건 조회 요청을 처리합니다.")
    fun getAudit(
        @Valid
        @RequestBody
        req: AuditSearchRequest,
    ): ResponseEntity<AuditSearchResponse> {
        log.info("======= [start] req: $req")
//        req.validate()

        val res = when (req) {
            is ImrAuditSearchRequest -> {
                log.info("##### [ImrAuditSearchRequest]: ${req.imrAuditId}")
                ImrAuditSearchResponse(
                    dataType = "imrAudit",
                    id = 1,
                    imrId = 2,
                    modified = LocalDateTime.now(),
                    data = "sample data"
                )
            }

            is EsignAuditSearchRequest -> {
                log.info("##### [EsignAuditSearchRequest]: ${req}")
                EsignAuditSearchResponse(
                    dataType = "esignAudit",
                    esignId = 1,
                    rev = 2,
                    modified = LocalDateTime.now(),
                    data = "sample data"
                )
            }

            is EsignVersionAuditSearchRequest -> {
                log.info("##### [EsignVersionAuditSearchRequest]: ${req}")
                EsignVersionAuditSearchResponse(
                    dataType = "esignVersionAudit",
                    no = 1,
                    rev = 2,
                    esignId = 3,
                    version = 4,
                    modified = LocalDateTime.now(),
                    dataAfter = "sample data after"
                )
            }

            is EsignOlsAuditSearchRequest -> {
                log.info("##### [EsignOlsAuditSearchRequest]: ${req}")
                EsignOlsAuditSearchResponse(
                    dataType = "esignOlsAudit",
                    memNo = "mem_1",
                    rev = 2,
                    modified = LocalDateTime.now(),
                    data = "sample data"
                )
            }

            else -> throw Exception("Kotlin exception")
        }
        log.info("======= end")

        return ResponseEntity.ok(res)
    }

    // TODO: name에 해당하는 [imrAudit, esignAudit, esignVersionAudit, esignOlsAudit] => 상수로 관리하기
    // TODO: request validation 처리하기 (현재는 없으면 0이 들어가고 있음.)
    @JsonTypeInfo(use = NAME, include = JsonTypeInfo.As.PROPERTY, property = "dataType")
    @JsonSubTypes(
        value = [
            JsonSubTypes.Type(value = ImrAuditSearchRequest::class, name = "imrAudit"),
            JsonSubTypes.Type(value = EsignAuditSearchRequest::class, name = "esignAudit"),
            JsonSubTypes.Type(value = EsignVersionAuditSearchRequest::class, name = "esignVersionAudit"),
            JsonSubTypes.Type(value = EsignOlsAuditSearchRequest::class, name = "esignOlsAudit")
        ]
    )
    interface AuditSearchRequest

    @JsonTypeName("imrAudit")
    data class ImrAuditSearchRequest(
        @ApiModelProperty(value = "IMR Audit ID", example = "10", required = true)
        @field:NotNull(message = "imrAuditId 값은 필수입니다.")
        val imrAuditId: Long?,  // Long 타입 선언하고, 요청시 해당 필드를 생략하면 0 값이 들어옴.
    ) : AuditSearchRequest

    @JsonTypeName("esignAudit")
    data class EsignAuditSearchRequest(
        @ApiModelProperty(value = "Esign ID", example = "13227", required = true)
        @field:NotNull(message = "esignId 값은 필수입니다.")
        val esignId: String?,
        @ApiModelProperty(value = "REV", example = "3", required = true)
        @field:NotNull(message = "rev 값은 필수입니다.")
        val rev: Long?,
    ) : AuditSearchRequest

    @JsonTypeName("esignVersionAudit")
    data class EsignVersionAuditSearchRequest(
        @ApiModelProperty(value = "No", example = "24419", required = true)
        @field:NotNull(message = "no 값은 필수입니다.")
        val no: Long?,
        @ApiModelProperty(value = "REV", example = "3", required = true)
        @field:NotNull(message = "rev 값은 필수입니다.")
        val rev: Long?,
    ) : AuditSearchRequest

    @JsonTypeName("esignOlsAudit")
    data class EsignOlsAuditSearchRequest(
        @ApiModelProperty(value = "Mem No", example = "200813000008", required = true)
        @field:NotNull(message = "memNo 값은 필수입니다.")
        val memNo: String?,
        @ApiModelProperty(value = "REV", example = "3", required = true)
        @field:NotNull(message = "rev 값은 필수입니다.")
        val rev: Long?,
    ) : AuditSearchRequest


//    data class AdminAuditResponse(val response: Response)

    interface AuditSearchResponse {
        val dataType: String
    }

    data class ImrAuditSearchResponse(
        override val dataType: String,
        val id: Long,
        val imrId: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditSearchResponse

    data class EsignAuditSearchResponse(
        override val dataType: String,
        val esignId: Long,
        val rev: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditSearchResponse

    data class EsignVersionAuditSearchResponse(
        override val dataType: String,
        val no: Long,
        val rev: Long,
        val esignId: Long,
        val version: Int,
        val modified: LocalDateTime,
        val dataAfter: String,
    ) : AuditSearchResponse

    data class EsignOlsAuditSearchResponse(
        override val dataType: String,
        val memNo: String,
        val rev: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditSearchResponse

    companion object {
        private val log = logger()
    }
}