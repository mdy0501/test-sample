package com.mdy.sample.stub

import com.mdy.sample.entity.Product
import com.mdy.sample.utils.TestUtils.genLong
import com.mdy.sample.utils.TestUtils.genString

object ProductStub {
    fun get(): Product {
        return Product.of(
            id = genLong(),
            name = genString(),
            price = genLong()
        )
    }

    fun getWithParams(
        id: Long? = null,
        name: String? = null,
        price: Long? = null
    ): Product {
        return Product.of(
            id = id ?: genLong(),
            name = name ?: genString(),
            price = price ?: genLong()
        )
    }
}