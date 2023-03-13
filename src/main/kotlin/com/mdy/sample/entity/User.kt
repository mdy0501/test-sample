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

//@Table(name = "users")
//@Table(name = "users", schema = "sample")
//@Entity
//class User private constructor(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long? = 0,
//
//    @Column(name = "name")
//    val name: String,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    val product: Product? = null
//){
//    companion object {
//        fun of(
//            id: Long = 0,
//            name: String,
//            product: Product?
//        ) = User(
//            name = name,
//            product = product,
//        )
//    }
//}

//@Table(name = "users", schema = "sample")
@Table(name = "users")
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(name = "name")
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    val product: Product? = null
){
    companion object {
        fun of(
            id: Long = 0,
            name: String,
            product: Product?
        ) = User(
            name = name,
            product = product,
        )
    }
}