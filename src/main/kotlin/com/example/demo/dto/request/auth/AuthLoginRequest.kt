package com.example.demo.dto.request.auth

import com.example.demo.dto.validation.ValidEmail
import com.example.demo.dto.validation.ValidPassword
import org.jetbrains.annotations.NotNull

data class AuthLoginRequest(
    @field:ValidEmail
    @field:NotNull
    val email: String,

    @field:ValidPassword
    @field:NotNull
    val password: Int,
)