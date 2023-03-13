package com.mdy.sample.integration

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.entity.Product
import com.mdy.sample.entity.User
import com.mdy.sample.repository.ProductRepository
import com.mdy.sample.repository.UserRepository
import com.mdy.sample.service.ProductService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional

@DisplayName("[IntegrationTest] ProductService")
@IntegrationTest
class ProductSerivceIntegrationTest(
    private val productService: ProductService,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
) {

    //    @AfterEach
    @BeforeAll
    fun deleteAll() {
        productRepository.deleteAll()
        userRepository.deleteAll()
    }

    //    @Transactional
    @Test
    fun a() {
        println("======================== 1")
        val givenProduct1 = productRepository.save(
            Product.of(
                name = "product_name_1",
                price = 1000L,
            )
        )
        val givenProduct2 = productRepository.save(
            Product.of(
                name = "product_name_2",
                price = 2000L,
            )
        )
        val givenUser1 = userRepository.save(
            User.of(
                name = "name1",
                product = givenProduct1
            )
        )
        val givenUser2 = userRepository.save(
            User.of(
                name = "name2",
                product = givenProduct1
            )
        )
        val givenUser3 = userRepository.save(
            User.of(
                name = "name3",
                product = givenProduct1
            )
        )

        val givenUser4 = userRepository.save(
            User.of(
                name = "name4",
                product = givenProduct2
            )
        )
        val givenUser5 = userRepository.save(
            User.of(
                name = "name5",
                product = givenProduct2
            )
        )
        println("======================== 2")

        val users = userRepository.findAll()
        println("### users size : ${users.size}")
        println("### users : $users")
        println("### users products: ${users.map { it.product }}")
//        val products = productRepository.findAll()
//        println("### products size : ${products.size}")
//        println("### products : $products")
        println("======================== 3")

    }

//    @DisplayName("get() - success test")
//    @Test
//    fun testGetSuccess() {
//        // given
//        val givenProduct = productRepository.save(ProductStub.get())
//
//        // when
//        val res = productService.get(id = givenProduct.id)
//
//        // then
//        assertEquals(givenProduct.id, res.id)
//        assertEquals(givenProduct.name, res.name)
//        assertEquals(givenProduct.price, res.price)
//    }
//
//    @DisplayName("get() - exception test")
//    @Test
//    fun testGetException() {
//        // given
//        val givenNotExistingProductId = genLong()
//
//        // when, then
//        assertThrows<Exception> {
//            productService.get(id = givenNotExistingProductId)
//        }
//    }
//
//    @DisplayName("register() - success test")
//    @Test
//    fun testRegisterSuccess() {
//        // given
//        val givenProduct = ProductStub.get()
//
//        // when
//        val res = productService.register(name = givenProduct.name, price = givenProduct.price)
//
//        // then
//        assertEquals(givenProduct.name, res.name)
//        assertEquals(givenProduct.price, res.price)
//    }
//
//    @DisplayName("update() - success test")
//    @Test
//    fun testUpdateSuccess() {
//        // given
//        val givenProduct = productRepository.save(ProductStub.get())
//        val givenChangeProductName = genString()
//        val givenChangeProductPrice = genLong().toFloat()
//
//        // when
//        val res = productService.update(
//            id = givenProduct.id,
//            name = givenChangeProductName,
//            price = givenChangeProductPrice
//        )
//
//        // then
//        assertEquals(givenProduct.id, res.id)
//        assertEquals(givenChangeProductName, res.name)
//        assertEquals(givenChangeProductPrice, res.price)
//    }
//
//    @DisplayName("update() - exception test")
//    @Test
//    fun testUpdateException() {
//        // given
//        val givenNotExistingProductId = genLong()
//        val givenProductName = genString()
//        val givenProductPrice = genLong().toFloat()
//
//        // when, then
//        assertThrows<Exception> {
//            productService.update(id = givenNotExistingProductId, name = givenProductName, price = givenProductPrice)
//        }
//    }
//
//    @DisplayName("remove() - success test")
//    @Test
//    fun testRemoveSuccess() {
//        // given
//        val givenProduct = productRepository.save(ProductStub.get())
//
//        // when
//        productService.remove(id = givenProduct.id)
//
//        // then
//        assertEquals(0, productRepository.findAll().size)
//    }
//
//    @DisplayName("remove() - exception test")
//    @Test
//    fun testRemoveException() {
//        // given
//        val givenNotExistingProductId = genLong()
//
//        // when, then
//        assertThrows<Exception> {
//            productService.remove(id = givenNotExistingProductId)
//        }
//    }
}