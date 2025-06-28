package com.example.demo.dto

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val error: String? = null
) {
    companion object {
        fun <T> success(data: T? = null, message: String = "Success"): ApiResponse<T> {
            return ApiResponse(success = true, message = message, data = data)
        }

        fun <T> error(error: String): ApiResponse<T> {
            return ApiResponse(success = false, message = "Request failed", error = error)
        }
    }
}