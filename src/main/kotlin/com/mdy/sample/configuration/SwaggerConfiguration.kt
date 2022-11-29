package com.mdy.sample.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()

    private fun apiInfo(): ApiInfo =
        ApiInfoBuilder()
            .title(API_TITLE)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .contact(Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
            .build()

    companion object {
        const val BASE_PACKAGE = "com.mdy.sample"
        const val API_VERSION = "0.0.1"
        const val API_TITLE = "Sample API"
        const val API_DESCRIPTION = "API Documentation"
        const val CONTACT_NAME = "sample-server"
        const val CONTACT_URL = "https://github.com/mdy0501/test-sample"
        const val CONTACT_EMAIL = "dongyeon89@gmail.com"
    }
}