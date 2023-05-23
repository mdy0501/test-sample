package com.mdy.sample.exception

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