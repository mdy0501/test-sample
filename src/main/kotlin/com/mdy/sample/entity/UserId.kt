package com.mdy.sample.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import kotlin.random.Random

@Embeddable
data class UserId(
    @Column(name = "email", nullable = false)
    val email: String,
    @Column(name = "id", nullable = false)
    val id: Long = Random.nextLong(from = 0, until = 10000000)
): Serializable {


    companion object {
        private const val serialVersionUID = 7456632265913047432L
    }

    override fun toString(): String {
        return "UserId(email='$email', id=$id)"
    }
}
