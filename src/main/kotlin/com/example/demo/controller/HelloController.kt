package com.example.demo.controller

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
class HelloController {

    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse("Hello World")
    }

    // GET dengan parameter
    @GetMapping("/hello/{name}")
    fun helloWithName(@PathVariable name: String): HelloResponse {
        return HelloResponse("Hello, $name!")
    }

    // GET dengan query parameter
    @GetMapping("/greet")
    fun greet(@RequestParam(defaultValue = "Friend") name: String): Map<String, String> {
        return mapOf(
            "greeting" to "Hello, $name!",
            "method" to "GET with query param"
        )
    }
}