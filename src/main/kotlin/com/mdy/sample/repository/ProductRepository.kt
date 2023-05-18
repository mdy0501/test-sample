package com.mdy.sample.repository

import com.mdy.sample.entity.Product
import com.mdy.sample.entity.QProduct.product as product
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

interface ProductRepository : ProductJpaRepository, ProductCustomRepository

interface ProductJpaRepository : JpaRepository<Product, Long> {
    fun findByName(name: String): Product?


    @Query(
        value = """
        select p
        from Product p
        where p.name = :name
    """
    )
    fun findByNameWithJPQL(@Param("name") name: String): Product?
}

interface ProductCustomRepository {
    fun findAllProducts(): List<Product>
    fun findAllByPrice(price: Float): List<Product>
    fun findByName(name: String): Product
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

    override fun findAllByPrice(price: Float): List<Product> {
        return jpaQueryFactory
            .select(product)
            .from(product)
            .where(product.price.eq(price))
            .fetch()
    }

    override fun findByName(name: String): Product {
        return jpaQueryFactory
            .select(product)
            .from(product)
            .where(product.name.eq(name))
            .fetchFirst()
    }
}