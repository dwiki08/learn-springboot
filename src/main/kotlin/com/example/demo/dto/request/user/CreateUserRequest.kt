package com.example.demo.dto.request.user

import com.example.demo.dto.validation.ValidAge
import com.example.demo.dto.validation.ValidEmail
import com.example.demo.dto.validation.ValidName
import com.example.demo.dto.validation.ValidPhone
import jakarta.validation.constraints.NotNull

data class CreateUserRequest(
    @field:ValidName
    @field:NotNull
    val name: String,

    @field:ValidEmail
    @field:NotNull
    val email: String,

    @field:ValidAge
    @field:NotNull
    val age: Int,

    @field:ValidPhone
    @field:NotNull
    val phone: String? = null
)