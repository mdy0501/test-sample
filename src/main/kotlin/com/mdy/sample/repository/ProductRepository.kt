package com.mdy.sample.repository

import com.mdy.sample.entity.Product
import com.mdy.sample.entity.QProduct.product
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ProductRepository : ProductJpaRepository, ProductCustomRepository

interface ProductJpaRepository : JpaRepository<Product, Long>

interface ProductCustomRepository {
    fun findAllProducts(): List<Product>
}

@Repository
class ProductCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : ProductCustomRepository {
    override fun findAllProducts(): List<Product> {
        return jpaQueryFactory
            .select(product)
            .from(product)
            .orderBy(product.id.desc())
            .fetch()
    }

}