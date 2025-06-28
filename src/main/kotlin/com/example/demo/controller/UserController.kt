package com.example.demo.controller

import com.example.demo.dto.ApiResponse
import com.example.demo.dto.request.user.CreateUserRequest
import com.example.demo.dto.request.user.UpdateUserRequest
import com.example.demo.dto.response.UserResponse
import com.example.demo.service.UserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Validated
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun createUser(@Valid @RequestBody request: CreateUserRequest): ResponseEntity<ApiResponse<UserResponse>> {
        return userService.createUser(request).fold(
            ifLeft = { error -> UserResponse.handleUserError(error) },
            ifRight = { user ->
                ResponseEntity.ok(ApiResponse.success(user, "User berhasil ditambahkan"))
            }
        )
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<ApiResponse<Map<String, Any>>> {
        return userService.getAllUser().fold(
            ifLeft = { error -> UserResponse.handleUserError(error) },
            ifRight = { data ->
                ResponseEntity.ok(
                    ApiResponse.success(
                        data = mapOf(
                            "data" to data,
                            "total" to data.size
                        )
                    )
                )
            }
        )
    }

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable
        @Min(
            1,
            message = "ID harus lebih dari 0"
        ) id: Long
    ): ResponseEntity<ApiResponse<UserResponse>> {
        return userService.getUserById(id).fold(
            ifLeft = { error -> UserResponse.handleUserError(error) },
            ifRight = { data ->
                ResponseEntity.ok(ApiResponse.success(data))
            }
        )
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateUserRequest
    ): ResponseEntity<ApiResponse<UserResponse>> {
        return userService.updateUser(id, request).fold(
            ifLeft = { error -> UserResponse.handleUserError(error) },
            ifRight = { user ->
                ResponseEntity.ok(ApiResponse.success(user, "User berhasil diupdate"))
            }
        )
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable @Min(
            1,
            message = "ID harus lebih dari 0"
        ) id: Long
    ): ResponseEntity<ApiResponse<Nothing>> {
        return userService.deleteUser(id).fold(
            ifLeft = { error -> UserResponse.handleUserError(error) },
            ifRight = { data ->
                ResponseEntity.ok(ApiResponse.success(message = "User berhasil dihapus"))
            }
        )
    }
}