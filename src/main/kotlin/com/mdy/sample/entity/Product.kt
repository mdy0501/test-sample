package com.mdy.sample.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

//@Table(name = "products", schema = "sample")
////@Table(name = "products")
//@Entity
//class Product private constructor(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//
//    @Column(name = "name")
//    var name: String,
//    @Column(name = "price")
//    var price: Long ? = null,
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_id", referencedColumnName = "id")
////    val user: User? = null
//
//) {
//
//    companion object {
//        fun of(
//            id: Long = 0,
//            name: String,
//            price: Long?,
//        ) = Product(
//            id = id,
//            name = name,
//            price = price,
//        )
//    }
//    fun updateName(name: String): Product {
//        this.name = name
//        return this
//    }
//
//    fun updatePrice(price: Long): Product {
//        this.price = price
//        return this
//    }
//
//
//    override fun toString(): String {
//        return "Product(id=$id, name='$name', price=$price)"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Product
//
//        if (id != other.id) return false
//        if (name != other.name) return false
//        if (price != other.price) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = id.hashCode()
//        result = 31 * result + name.hashCode()
//        result = 31 * result + (price?.hashCode() ?: 0)
//        return result
//    }
//
//
//}

//@Table(name = "products", schema = "sample")
@Table(name = "products")
@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String,
    @Column(name = "price")
    var price: Long?,
) {

    companion object {
        fun of(
            id: Long = 0,
            name: String,
            price: Long? = null,
        ) = Product(
            id = id,
            name = name,
            price = price,
        )
    }
    fun updateName(name: String): Product {
        this.name = name
        return this
    }

    fun updatePrice(price: Long): Product {
        this.price = price
        return this
    }
}
