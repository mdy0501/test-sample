package com.mdy.sample.e2e

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.dto.DeleteProductResponse
import com.mdy.sample.dto.GetProductResponse
import com.mdy.sample.dto.PostProductRequest
import com.mdy.sample.dto.PostProductResponse
import com.mdy.sample.dto.PutProductRequest
import com.mdy.sample.dto.PutProductResponse
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.stub.ProductStub
import com.mdy.sample.utils.TestUtils.genLong
import com.mdy.sample.utils.TestUtils.genString
import com.mdy.sample.utils.logger
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@DisplayName("[E2E] /api/product")
@IntegrationTest
class ProductE2ETest(
    private val productRepository: ProductRepository,
    private val testRestTemplate: TestRestTemplate,
) {

    @AfterEach
    fun deleteAll() {
        productRepository.deleteAll()
    }

    @DisplayName("[GET /{id}] 상품을 id로 조회한다.")
    @Test
    fun testGetProduct() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())

        // when
        val res = testRestTemplate.getForEntity<GetProductResponse>(
            url = "/api/product/${givenProduct.id}",
            GetProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProduct.id.toString(), res.body?.productId)
        assertEquals(givenProduct.name, res.body?.name)
        assertEquals(givenProduct.price, res.body?.price)
    }


    @DisplayName("[POST] 상품을 등록한다.")
    @Test
    fun testPostProduct() {
        // given
        val givenProduct = ProductStub.get()
        val request = PostProductRequest(
            name = givenProduct.name,
            price = givenProduct.price,
        )

        // when
        val res = testRestTemplate.postForEntity<PostProductResponse>(
            url = "/api/product",
            request = request,
            PostProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProduct.name, res.body?.name)
        assertEquals(givenProduct.price, res.body?.price)
    }

    @DisplayName("[PUT /{id}] 상품 정보를 수정한다.")
    @Test
    fun testPutProduct() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())
        val givenChangeName = "product-name-${genString()}"
        val givenChangePrice = genLong().toFloat()
        val requestDto = PutProductRequest(
            name = givenChangeName,
            price = givenChangePrice,
        )

        // when
        val requestEntity = HttpEntity<PutProductRequest>(requestDto)
        val res = testRestTemplate.exchange(
            "/api/product/${givenProduct.id}",
            HttpMethod.PUT,
            requestEntity,
            PutProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProduct.id.toString(), res.body?.productId)
        assertEquals(givenChangeName, res.body?.name)
        assertEquals(givenChangePrice, res.body?.price)
    }

    @DisplayName("[DELETE /{id}] 상품을 삭제한다.")
    @Test
    fun testDeleteProduct() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())
        val givenSuccessMessage = "Success"

        // when
        val res = testRestTemplate.exchange(
            "/api/product/${givenProduct.id}",
            HttpMethod.DELETE,
            HttpEntity.EMPTY,
            DeleteProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenSuccessMessage, res.body?.message)
    }

    companion object {
        private val log = logger()
    }
}