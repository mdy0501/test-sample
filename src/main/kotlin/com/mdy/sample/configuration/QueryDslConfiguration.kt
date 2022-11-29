package com.mdy.sample.configuration

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class QueryDslConfiguration {
    @PersistenceContext
    private val entityManager: EntityManager? = null

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory? = JPAQueryFactory(entityManager)
}