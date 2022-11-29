package com.mdy.sample.integration

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.service.ProductService
import com.mdy.sample.stub.ProductStub
import com.mdy.sample.utils.TestUtils.genLong
import com.mdy.sample.utils.TestUtils.genString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("[IntegrationTest] ProductService")
@IntegrationTest
class ProductSerivceIntegrationTest(
    private val productService: ProductService,
    private val productRepository: ProductRepository,
) {

    @AfterEach
    fun deleteAll() {
        productRepository.deleteAll()
    }

    @DisplayName("get() - success test")
    @Test
    fun testGetSuccess() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())

        // when
        val res = productService.get(id = givenProduct.id)

        // then
        assertEquals(givenProduct.id, res.id)
        assertEquals(givenProduct.name, res.name)
        assertEquals(givenProduct.price, res.price)
    }

    @DisplayName("get() - exception test")
    @Test
    fun testGetException() {
        // given
        val givenNotExistingProductId = genLong()

        // when, then
        assertThrows<Exception> {
            productService.get(id = givenNotExistingProductId)
        }
    }

    @DisplayName("register() - success test")
    @Test
    fun testRegisterSuccess() {
        // given
        val givenProduct = ProductStub.get()

        // when
        val res = productService.register(name = givenProduct.name, price = givenProduct.price)

        // then
        assertEquals(givenProduct.name, res.name)
        assertEquals(givenProduct.price, res.price)
    }

    @DisplayName("update() - success test")
    @Test
    fun testUpdateSuccess() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())
        val givenChangeProductName = genString()
        val givenChangeProductPrice = genLong().toFloat()

        // when
        val res = productService.update(
            id = givenProduct.id,
            name = givenChangeProductName,
            price = givenChangeProductPrice
        )

        // then
        assertEquals(givenProduct.id, res.id)
        assertEquals(givenChangeProductName, res.name)
        assertEquals(givenChangeProductPrice, res.price)
    }

    @DisplayName("update() - exception test")
    @Test
    fun testUpdateException() {
        // given
        val givenNotExistingProductId = genLong()
        val givenProductName = genString()
        val givenProductPrice = genLong().toFloat()

        // when, then
        assertThrows<Exception> {
            productService.update(id = givenNotExistingProductId, name = givenProductName, price = givenProductPrice)
        }
    }

    @DisplayName("remove() - success test")
    @Test
    fun testRemoveSuccess() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())

        // when
        productService.remove(id = givenProduct.id)

        // then
        assertEquals(0, productRepository.findAll().size)
    }

    @DisplayName("remove() - exception test")
    @Test
    fun testRemoveException() {
        // given
        val givenNotExistingProductId = genLong()

        // when, then
        assertThrows<Exception> {
            productService.remove(id = givenNotExistingProductId)
        }
    }
}