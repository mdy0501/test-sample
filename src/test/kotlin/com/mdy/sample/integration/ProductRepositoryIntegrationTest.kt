package com.mdy.sample.integration

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.stub.ProductStub
import com.mdy.sample.utils.logger
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[IntegrationTest] ProductRepository")
@IntegrationTest
class ProductRepositoryIntegrationTest(
    private val productRepository: ProductRepository,
) {
    @AfterEach
    fun deleteAll() {
        productRepository.deleteAll()
    }

    @DisplayName("findAllProducts() test")
    @Test
    fun test() {
        // given
        productRepository.saveAll(
            listOf(
                ProductStub.get(),
                ProductStub.get(),
                ProductStub.get(),
                ProductStub.get(),
                ProductStub.get(),
            )
        )

        // when
        val res = productRepository.findAllProducts()
        log.info("# size: ${res.size}")

        // then
        assertEquals(5, res.size)
    }

    companion object {
        private val log = logger()
    }
}