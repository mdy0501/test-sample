package com.mdy.sample.annotation

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@Tag("IntegrationTest")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("integration-test")
@ComponentScan(BASE_PACKAGE)
@EnableJpaRepositories(BASE_PACKAGE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@PropertySources(value = [PropertySource("classpath:application-integration-test.yml")])
annotation class IntegrationTest

const val BASE_PACKAGE = "com.mdy.sample"