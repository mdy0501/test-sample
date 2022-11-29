package com.mdy.sample.integration

import com.mdy.sample.annotation.IntegrationTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("JUnit - before, after 기능 확인")
@IntegrationTest
class JUnitFunctionTest {

    @BeforeEach
    fun beforeEach() {
        println("### before each")
    }

    @BeforeAll
    fun beforeAll() {
        println("### before all")
    }

    @AfterEach
    fun afterEach() {
        println("### after each")
    }

    @AfterAll
    fun afterAll() {
        println("### after all")
    }

    @Test
    fun firstTest() {
        println("### test 1")
    }

    @Test
    fun secondTest() {
        println("### test 2")
    }
}