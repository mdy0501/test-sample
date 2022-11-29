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

    @DisplayName("findAllByPrice() test")
    @Test
    fun testFindAllByPrice() {
        // given
        val givenPrice = 2000f
        val givenProductList = productRepository.saveAll(
            listOf(
                ProductStub.getWithParams(price = givenPrice),
                ProductStub.getWithParams(price = givenPrice),
                ProductStub.getWithParams(price = givenPrice),
                ProductStub.getWithParams(price = givenPrice),
                ProductStub.getWithParams(price = givenPrice),
            )
        )

        // when
        val res = productRepository.findAllByPrice(price = givenPrice)
        log.info("# res: $res")

        // then
        assertEquals(givenProductList.size, res.size)
    }

    @DisplayName("findByName() test")
    @Test
    fun testFindByName() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())

        // when
        val res = productRepository.findByName(name = givenProduct.name)

        // then
        assertEquals(givenProduct.id, res.id)
        assertEquals(givenProduct.name, res.name)
        assertEquals(givenProduct.price, res.price)
    }

    companion object {
        private val log = logger()
    }
}