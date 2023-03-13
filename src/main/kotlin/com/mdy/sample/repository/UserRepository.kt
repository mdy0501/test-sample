package com.mdy.sample.repository

import com.mdy.sample.entity.Product
import com.mdy.sample.entity.User
import com.mdy.sample.entity.QProduct.product as product
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository : UserJpaRepository
//    , ProductCustomRepository

interface UserJpaRepository : JpaRepository<User, Long>

//interface ProductCustomRepository {
//    fun findAllProducts(): List<Product>
//    fun findAllByPrice(price: Float): List<Product>
//    fun findByName(name: String): Product
//}