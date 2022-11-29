package com.mdy.sample.unit

import com.mdy.sample.annotation.UnitTest
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.service.ProductService
import com.mdy.sample.stub.ProductStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull

@DisplayName("[UnitTest] ProductService")
@UnitTest
class ProductServiceUnitTest {
    private val productRepository: ProductRepository = mockk()
    private val productService: ProductService = ProductService(
        productRepository = productRepository
    )

    @DisplayName("get() - success test")
    @Test
    fun testGetSuccess() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.findByIdOrNull(id = any()) } returns givenProduct

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
        val givenProduct = ProductStub.get()

        every { productRepository.findByIdOrNull(id = any()) } returns null

        // when, then
        assertThrows<Exception> {
            productService.get(id = givenProduct.id)
        }
    }

    @DisplayName("register() - success test")
    @Test
    fun testRegisterSuccess() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.save(any()) } returns givenProduct

        // when
        val res = productService.register(name = givenProduct.name, price = givenProduct.price)

        // then
        assertEquals(givenProduct.id, res.id)
        assertEquals(givenProduct.name, res.name)
        assertEquals(givenProduct.price, res.price)
    }

    @DisplayName("update() - success test")
    @Test
    fun testUpdateSuccess() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.findByIdOrNull(id = any()) } returns givenProduct
        every { productRepository.save(any()) } returns givenProduct

        // when
        val res = productService.update(id = givenProduct.id, name = givenProduct.name, price = givenProduct.price)

        // then
        assertEquals(givenProduct.id, res.id)
        assertEquals(givenProduct.name, res.name)
        assertEquals(givenProduct.price, res.price)
    }

    @DisplayName("update() - exception test")
    @Test
    fun testUpdateException() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.findByIdOrNull(id = any()) } returns null

        // when, then
        assertThrows<Exception> {
            productService.update(id = givenProduct.id, name = givenProduct.name, price = givenProduct.price)
        }
    }

    @DisplayName("remove() - success test")
    @Test
    fun testRemoveSuccess() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.findByIdOrNull(id = any()) } returns givenProduct
        every { productRepository.deleteById(any()) } returns Unit

        // when
        productService.remove(id = givenProduct.id)

        // then
        verify {
            productRepository.deleteById(givenProduct.id)
        }
    }

    @DisplayName("remove() - exception test")
    @Test
    fun testRemoveException() {
        // given
        val givenProduct = ProductStub.get()
        every { productRepository.findByIdOrNull(id = any()) } returns null

        // when, then
        assertThrows<Exception> {
            productService.remove(id = givenProduct.id)
        }
    }
}