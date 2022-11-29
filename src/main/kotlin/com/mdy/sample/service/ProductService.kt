package com.mdy.sample.service

import com.mdy.sample.entity.Product
import com.mdy.sample.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    /**
     * 상품 조회
     */
    fun get(id: Long): Product {
        return productRepository.findByIdOrNull(id = id)
            ?: throw Exception("Not found product - productId: $id")
    }

    /**
     * 상품 생성
     */
    fun register(
        name: String,
        price: Float,
    ): Product {
        val product = Product(
            name = name,
            price = price
        )
        return productRepository.save(product)
    }

    /**
     * 상품 정보 변경 (name, price)
     */
    fun update(
        id: Long,
        name: String?,
        price: Float?
    ): Product {
        val foundProduct = productRepository.findByIdOrNull(id = id)
            ?: throw Exception("Not found product - productId: $id")

        name?.let {
            foundProduct.updateName(it)
        }
        price?.let {
            foundProduct.updatePrice(it)
        }

        return productRepository.save(foundProduct)
    }

    /**
     * 상품 제거
     */
    fun remove(id: Long) {
        productRepository.findByIdOrNull(id = id)
            ?: throw Exception("Not found product - productId: $id")

        productRepository.deleteById(id)
    }
}