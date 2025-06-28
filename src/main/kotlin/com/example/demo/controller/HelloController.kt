package com.example.demo.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

data class HelloResponse(
    val message: String,
    val timestamp: String = java.time.LocalDateTime.now().toString()
)

data class User(
    val id: Long? = null,
    val name: String,
    val email: String
)

@RestController
@RequestMapping("/api")
@Tag(name = "Hello API", description = "Simple greeting operations")
class HelloController {

    @GetMapping("/hello")
    @Operation(
        summary = "Simple hello endpoint",
        description = "Returns a simple hello world message"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successful response",
        content = [Content(mediaType = "application/json")]
    )
    fun hello(): HelloResponse {
        return HelloResponse("Hello World")
    }

    @GetMapping("/hello/{name}")
    @Operation(
        summary = "Hello with name",
        description = "Returns a personalized greeting message"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful response"),
            ApiResponse(responseCode = "400", description = "Invalid name provided")
        ]
    )
    fun helloWithName(
        @Parameter(
            description = "Name of the person to greet",
            example = "John",
            required = true
        )
        @PathVariable name: String
    ): HelloResponse {
        return HelloResponse("Hello, $name!")
    }

    @GetMapping("/greet")
    @Operation(
        summary = "Greet with query parameter",
        description = "Returns a greeting message using query parameter with default value"
    )
    @ApiResponse(responseCode = "200", description = "Successful response")
    fun greet(
        @Parameter(
            description = "Name of the person to greet (optional)",
            example = "Alice"
        )
        @RequestParam(defaultValue = "Friend") name: String
    ): Map<String, String> {
        return mapOf(
            "greeting" to "Hello, $name!",
            "method" to "GET with query param"
        )
    }
}