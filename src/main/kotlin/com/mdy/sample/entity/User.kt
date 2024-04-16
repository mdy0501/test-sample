package com.mdy.sample.entity

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "users", schema = "sample")
@Entity
data class User(
    @EmbeddedId
    val userId: UserId,

    @Column(name = "name")
    var name: String,
) {
    override fun toString(): String {
        return "User(userId=$userId, name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }
}
