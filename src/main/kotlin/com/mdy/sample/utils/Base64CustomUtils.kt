package com.mdy.sample.utils

import java.util.Base64

object Base64CustomUtils {
    fun encode(value: String): String =
        Base64.getEncoder().encodeToString(value.toByteArray())

    fun decode(encodedValue: String): String =
        String(Base64.getDecoder().decode(encodedValue))
}
