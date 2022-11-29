package com.mdy.sample.utils

import java.util.UUID
import kotlin.random.Random as KRandom

object TestUtils {
    fun genString() = UUID.randomUUID().toString()
    fun genLong(until: Long = Long.MAX_VALUE): Long = KRandom.nextLong(from = 0, until = until)
    fun genInt(until: Int = Int.MAX_VALUE): Int = KRandom.nextInt(from = 0, until = until)
}
