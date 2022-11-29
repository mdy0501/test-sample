package com.mdy.sample.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "products", schema = "sample")
@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String,
    @Column(name = "price")
    var price: Float,
) {
    fun updateName(name: String): Product {
        this.name = name
        return this
    }

    fun updatePrice(price: Float): Product {
        this.price = price
        return this
    }
}
