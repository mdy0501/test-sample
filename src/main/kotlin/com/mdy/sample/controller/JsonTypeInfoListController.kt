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

@RequestMapping("/api/json-type-info/list")
@RestController
class JsonTypeInfoListController {

    @PostMapping("/list")
    @Operation(summary = "감사 목록 테스트", description = "감사 목록 조회를 위한 요청을 처리합니다.")
    fun auditList(
        @RequestBody
        req: AuditListSearchRequest,
    ): ResponseEntity<AuditListSearchResponse> {
        log.info("======= start")
        log.info("======= req: $req")

        val res = when (req) {
            is ImrAuditListSearchRequest -> {
                log.info("##### [ImrAuditListSearchRequest]: ${req.imrId}")
                ImrAuditListSearchResponse(
                    dataType = "imrAudit",
                    id = 1,
                    imrId = 2,
                    modified = LocalDateTime.now(),
                    data = "sample data"
                )
            }

            is EsignAuditListSearchRequest -> {
                log.info("##### [EsignAuditListSearchRequest]: ${req}")
                EsignAuditListSearchResponse(
                    dataType = "esignAudit",
                    esignId = 1,
                    rev = 2,
                    modified = LocalDateTime.now(),
                    data = "sample data"
                )
            }

            is EsignVersionAuditListSearchRequest -> {
                log.info("##### [EsignVersionAuditListSearchRequest]: ${req}")
                EsignVersionAuditListSearchResponse(
                    dataType = "esignVersionAudit",
                    no = 1,
                    rev = 2,
                    esignId = 3,
                    version = 4,
                    modified = LocalDateTime.now(),
                    dataAfter = "sample data after"
                )
            }

            is EsignOlsAuditListSearchRequest -> {
                log.info("##### [EsignOlsAuditListSearchRequest]: ${req}")
                EsignOlsAuditListSearchResponse(
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
            JsonSubTypes.Type(value = ImrAuditListSearchRequest::class, name = "imrAudit"),
            JsonSubTypes.Type(value = EsignAuditListSearchRequest::class, name = "esignAudit"),
            JsonSubTypes.Type(value = EsignVersionAuditListSearchRequest::class, name = "esignVersionAudit"),
            JsonSubTypes.Type(value = EsignOlsAuditListSearchRequest::class, name = "esignOlsAudit")
        ]
    )
    interface AuditListSearchRequest

    @JsonTypeName("imrAudit")
    data class ImrAuditListSearchRequest(
        @ApiModelProperty(value = "IMR ID", example = "10", required = true)
        val imrId: Long,
    ) : AuditListSearchRequest

    @JsonTypeName("esignAudit")
    data class EsignAuditListSearchRequest(
//        @Schema(description = "E-Sign ID")
        @ApiModelProperty(value = "Esign ID", example = "13227", required = true)
        val esignId: Long,
    ) : AuditListSearchRequest

    @JsonTypeName("esignVersionAudit")
    data class EsignVersionAuditListSearchRequest(
//        @Schema(description = "E-Sign ID")
        @ApiModelProperty(value = "Esign ID", example = "13227", required = true)
        val esignId: Long,
//        @Schema(description = "버전")
        @ApiModelProperty(value = "Esign Version", example = "2", required = false)
        val version: Int,
    ) : AuditListSearchRequest

    @JsonTypeName("esignOlsAudit")
    data class EsignOlsAuditListSearchRequest(
//        @Schema(description = "멤버 번호")
        @ApiModelProperty(value = "Mem No", example = "200813000008", required = true)
        val memNo: String,
    ) : AuditListSearchRequest


//    data class AdminAuditResponse(val response: Response)

    interface AuditListSearchResponse {
        val dataType: String
    }

    data class ImrAuditListSearchResponse(
        override val dataType: String,
        val id: Long,
        val imrId: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditListSearchResponse

    data class EsignAuditListSearchResponse(
        override val dataType: String,
        val esignId: Long,
        val rev: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditListSearchResponse

    data class EsignVersionAuditListSearchResponse(
        override val dataType: String,
        val no: Long,
        val rev: Long,
        val esignId: Long,
        val version: Int,
        val modified: LocalDateTime,
        val dataAfter: String,
    ) : AuditListSearchResponse

    data class EsignOlsAuditListSearchResponse(
        override val dataType: String,
        val memNo: String,
        val rev: Long,
        val modified: LocalDateTime,
        val data: String,
    ) : AuditListSearchResponse

    companion object {
        private val log = logger()
    }
}