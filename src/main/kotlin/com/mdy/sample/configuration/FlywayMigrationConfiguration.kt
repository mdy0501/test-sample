package com.mdy.sample.configuration

import com.mdy.sample.utils.logger
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Configuration
@ConditionalOnProperty("spring.flyway.enabled")
class FlywayMigrationConfiguration {

    @Bean
    @Order(Int.MIN_VALUE)
    fun flywayMigrationInitializer(flyway: Flyway): FlywayMigrationInitializer =
        FlywayMigrationInitializer(flyway) { f: Flyway ->
            val config = f.configuration

            config.schemas.map { schema ->
                log.info("flyway migrate schema=$schema")

                Flyway.configure()
                    .configuration(config)
                    .schemas(schema)
                    .locations("classpath:db/migration/$schema")
                    .load()
                    .migrate()
            }
        }

    companion object {
        private val log = logger()
    }
}
