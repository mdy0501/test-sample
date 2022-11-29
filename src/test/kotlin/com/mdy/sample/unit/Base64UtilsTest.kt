package com.mdy.sample.unit

import com.mdy.sample.annotation.UnitTest
import com.mdy.sample.utils.Base64CustomUtils
import com.mdy.sample.utils.logger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.Base64

@DisplayName("[UnitTest] Base64 util function")
@UnitTest
class Base64UtilsTest {

    @DisplayName("encode test")
    @Test
    fun testEncoder() {
        // given
        val givenInputString = "secretKey"
        val encodedInputString = Base64.getEncoder().encodeToString(givenInputString.toByteArray())

        // when
        val res = Base64CustomUtils.encode(value = givenInputString)
        log.info("# res: $res")

        // then
        assertEquals(encodedInputString, res)
    }

    @DisplayName("decode test")
    @Test
    fun testDecoder() {
        // given
        val givenEncodedValue = "c2VjcmV0S2V5"
        val decodedGivenValue = String(Base64.getDecoder().decode(givenEncodedValue))

        // when
        val res = Base64CustomUtils.decode(encodedValue = givenEncodedValue)
        log.info("# res: $res")

        // then
        assertEquals(decodedGivenValue, res)
    }

    companion object {
        private val log = logger()
    }
}
