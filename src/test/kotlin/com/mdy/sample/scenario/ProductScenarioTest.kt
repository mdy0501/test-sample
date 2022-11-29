package com.mdy.sample.scenario

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.dto.DeleteProductResponse
import com.mdy.sample.dto.GetProductResponse
import com.mdy.sample.dto.PostProductRequest
import com.mdy.sample.dto.PostProductResponse
import com.mdy.sample.dto.PutProductRequest
import com.mdy.sample.dto.PutProductResponse
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.stub.ProductStub
import com.mdy.sample.utils.TestUtils
import com.mdy.sample.utils.TestUtils.genString
import com.mdy.sample.utils.logger
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@DisplayName("[Scenario] 상품 시나리오 테스트")
@IntegrationTest
class ProductScenarioTest(
    private val productRepository: ProductRepository,
    private val testRestTemplate: TestRestTemplate,
) {

    @AfterAll
    fun deleteAll() {
        productRepository.deleteAll()
    }

    class Context {
        companion object {
            var givenProductId: String? = null
        }
    }

    /**
     * [Scenario]
     *  1. 상품을 생성한다.
     *  2. 생성한 상품을 조회한다.
     *  3. 존재하지 않는 상품으로 조회하면 exception 발생
     *  4. 상품의 이름을 변경한다.
     *  5. 상품의 가격을 변경한다.
     *  6. 상품의 이름과 가격을 둘다 변경한다.
     *  7. 존재하지 않는 상품 id로 이름과 가격을 변경하면 exception 발생
     *  8. 상품을 삭제한다.
     *  9. 상품이 삭제된 것을 확인한다.
     */

    @Order(1)
    @DisplayName("1. 상품을 생성한다.")
    @Test
    fun scenario_1() {
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
        Context.givenProductId = res.body?.productId
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProduct.name, res.body?.name)
        assertEquals(givenProduct.price, res.body?.price)
    }

    @Order(2)
    @DisplayName("2. 생성한 상품을 조회한다.")
    @Test
    fun scenario_2() {
        // given
        val givenProductId = Context.givenProductId!!

        // when
        val res = testRestTemplate.getForEntity<GetProductResponse>(
            url = "/api/product/${givenProductId}",
            GetProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        val foundProduct = productRepository.findByIdOrNull(id = givenProductId.toLong())!!
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProductId, res.body?.productId)
        assertEquals(foundProduct.name, res.body?.name)
        assertEquals(foundProduct.price, res.body?.price)
    }

    @Order(3)
    @DisplayName("3. 존재하지 않는 상품으로 조회하면 exception 발생.")
    @Test
    fun scenario_3() {
        // given
        val givenNotExistingProductId = genString()

        // when, then
        assertThrows<Exception> {
            testRestTemplate.getForEntity<GetProductResponse>(
                url = "/api/product/${givenNotExistingProductId}",
                GetProductResponse::class.java
            )
        }
    }

    @Order(4)
    @DisplayName("4. 상품의 이름을 변경한다.")
    @Test
    fun scenario_4() {
        // given
        val givenProductId = Context.givenProductId
        val givenChangeName = "product-name-${genString()}"
        val requestDto = PutProductRequest(
            name = givenChangeName,
            price = null,
        )
        val requestEntity = HttpEntity<PutProductRequest>(requestDto)

        // when
        val res = testRestTemplate.exchange(
            "/api/product/${givenProductId}",
            HttpMethod.PUT,
            requestEntity,
            PutProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProductId, res.body?.productId)
        assertEquals(givenChangeName, res.body?.name)
    }

    @Order(5)
    @DisplayName("5. 상품의 가격을 변경한다.")
    @Test
    fun scenario_5() {
        // given
        val givenProductId = Context.givenProductId
        val givenChangePrice = TestUtils.genLong().toFloat()
        val requestDto = PutProductRequest(
            name = null,
            price = givenChangePrice,
        )
        val requestEntity = HttpEntity<PutProductRequest>(requestDto)

        // when
        val res = testRestTemplate.exchange(
            "/api/product/${givenProductId}",
            HttpMethod.PUT,
            requestEntity,
            PutProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenProductId, res.body?.productId)
        assertEquals(givenChangePrice, res.body?.price)
    }

    @Order(6)
    @DisplayName("6. 상품의 이름과 가격을 둘다 변경한다.")
    @Test
    fun scenario_6() {
        // given
        val givenProduct = productRepository.save(ProductStub.get())
        val givenChangeName = "product-name-${genString()}"
        val givenChangePrice = TestUtils.genLong().toFloat()
        val requestDto = PutProductRequest(
            name = givenChangeName,
            price = givenChangePrice,
        )
        val requestEntity = HttpEntity<PutProductRequest>(requestDto)

        // when
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

    @Order(7)
    @DisplayName("7. 존재하지 않는 상품 id로 이름과 가격을 변경하면 exception 발생")
    @Test
    fun scenario_7() {
        // given
        val givenNotExistingProductId = genString()
        val givenChangeName = "product-name-${genString()}"
        val givenChangePrice = TestUtils.genLong().toFloat()
        val requestDto = PutProductRequest(
            name = givenChangeName,
            price = givenChangePrice,
        )
        val requestEntity = HttpEntity<PutProductRequest>(requestDto)

        // when, then
        assertThrows<Exception> {
            testRestTemplate.exchange(
                "/api/product/${givenNotExistingProductId}",
                HttpMethod.PUT,
                requestEntity,
                PutProductResponse::class.java
            )
        }
    }

    @Order(8)
    @DisplayName("8. 상품을 삭제한다.")
    @Test
    fun scenario_8() {
        // given
        val givenProductId = Context.givenProductId
        val givenSuccessMessage = "Success"

        // when
        val res = testRestTemplate.exchange(
            "/api/product/${givenProductId}",
            HttpMethod.DELETE,
            HttpEntity.EMPTY,
            DeleteProductResponse::class.java
        )
        log.info("# res: $res")

        // then
        assertEquals(HttpStatus.OK, res.statusCode)
        assertEquals(givenSuccessMessage, res.body?.message)
    }

    @Order(9)
    @DisplayName("9. 상품이 삭제된 것을 확인한다.")
    @Test
    fun scenario_9() {
        // given
        val givenProductId = Context.givenProductId

        // when, then
        assertThrows<Exception> {
            testRestTemplate.getForEntity<GetProductResponse>(
                url = "/api/product/${givenProductId}",
                GetProductResponse::class.java
            )
        }
    }

    companion object {
        private val log = logger()
    }
}