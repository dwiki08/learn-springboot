package com.example.demo.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI().apply {
            info = Info().apply {
                title = "API Documentation"
                version = "1.0.0"
                description = "Dokumentasi API untuk aplikasi Spring Boot dengan Kotlin"
                contact = Contact().apply {
                    name = "dwiki08"
                    url = "https://github.com/dwiki08"
                }
            }
            servers = listOf(
                Server().url("http://localhost:8080").description("Development Server")
            )
        }
    }
}