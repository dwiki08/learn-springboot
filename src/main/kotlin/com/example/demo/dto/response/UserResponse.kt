package com.example.demo.dto.response

import com.example.demo.dto.ApiResponse
import com.example.demo.entity.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val age: Int,
    val phone: String? = null,
    val createdAt: String,
    val updatedAt: String
) {
    companion object {
        sealed class UserError(val message: String) {
            data class UserNotFound(val identifier: String) : UserError("User dengan $identifier tidak ditemukan")
            data class EmailAlreadyExists(val email: String) : UserError("Email $email sudah digunakan")
            data class InvalidInput(val details: String) : UserError("Input tidak valid: $details")
            object UnknownError : UserError("Terjadi kesalahan")
        }

        fun fromEntity(user: User): UserResponse {
            return UserResponse(
                id = user.id ?: -1,
                name = user.name,
                email = user.email,
                age = user.age,
                phone = user.phone,
                createdAt = user.createdAt.toString(),
                updatedAt = user.updatedAt.toString()
            )
        }

        fun <T> handleUserError(error: UserError): ResponseEntity<ApiResponse<T>> {
            return when (error) {
                is UserError.UserNotFound -> {
                    ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error(error.message))
                }

                is UserError.EmailAlreadyExists -> {
                    ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(ApiResponse.error(error.message))
                }

                is UserError.InvalidInput -> {
                    ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.error(error.message))
                }

                else -> {
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ApiResponse.error(error.message))
                }
            }
        }
    }
}