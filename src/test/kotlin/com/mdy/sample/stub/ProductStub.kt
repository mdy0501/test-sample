package com.mdy.sample.stub

import com.mdy.sample.entity.Product
import com.mdy.sample.utils.TestUtils.genLong
import com.mdy.sample.utils.TestUtils.genString

object ProductStub {
    fun get(): Product {
        return Product(
            id = genLong(),
            name = genString(),
            price = genLong().toFloat()
        )
    }
}