package com.example.demo.dto.request.user

import com.example.demo.dto.validation.ValidAge
import com.example.demo.dto.validation.ValidName
import com.example.demo.dto.validation.ValidPhone

data class UpdateUserRequest(
    @field:ValidName
    val name: String,

    @field:ValidAge
    val age: Int,

    @field:ValidPhone
    val phone: String? = null
)