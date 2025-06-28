package com.example.demo.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.demo.dto.request.user.CreateUserRequest
import com.example.demo.dto.request.user.UpdateUserRequest
import com.example.demo.dto.response.UserResponse
import com.example.demo.dto.response.UserResponse.Companion.UserError
import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getAllUser(queryName: String? = null): Either<UserError, List<UserResponse>> {
        return try {
            val users = if (queryName != null)
                userRepository.findByNameContainingIgnoreCase(queryName) else userRepository.findAll()
            users.map { UserResponse.fromEntity(it) }.right()
        } catch (_: Exception) {
            UserError.UnknownError.left()
        }
    }

    @Transactional(readOnly = true)
    fun getUserById(id: Long): Either<UserError, UserResponse> {
        return try {
            val data = userRepository.findById(id).map { UserResponse.fromEntity(it) }
            if (data.isPresent) {
                data.get().right()
            } else {
                UserError.UserNotFound("ID $id").left()
            }
        } catch (_: Exception) {
            UserError.UnknownError.left()
        }
    }

    fun createUser(request: CreateUserRequest): Either<UserError, UserResponse> {
        return try {
            if (userRepository.existsByEmail(request.email)) {
                UserError.EmailAlreadyExists(request.email).left()
            } else {
                val user = User(
                    name = request.name,
                    email = request.email,
                    age = request.age,
                    phone = request.phone
                )
                val savedUser = userRepository.save(user)
                UserResponse.fromEntity(savedUser).right()
            }
        } catch (_: Exception) {
            UserError.UnknownError.left()
        }
    }

    fun updateUser(id: Long, request: UpdateUserRequest): Either<UserError, UserResponse> {
        return try {
            val existingUser = userRepository.findById(id)
                .orElse(null) ?: return UserError.UserNotFound("ID $id").left()

            val updatedUser = existingUser.copy(
                name = request.name ?: existingUser.name,
                phone = request.phone ?: existingUser.phone,
                age = request.age ?: existingUser.age,
                updatedAt = LocalDateTime.now()
            )

            val savedUser = userRepository.save(updatedUser)
            UserResponse.fromEntity(savedUser).right()
        } catch (_: Exception) {
            UserError.UnknownError.left()
        }
    }

    fun deleteUser(id: Long): Either<UserError, Boolean> {
        return try {
            if (!userRepository.existsById(id)) {
                return UserError.UserNotFound("ID $id").left()
            }
            userRepository.deleteById(id)
            return true.right()
        } catch (_: Exception) {
            UserError.UnknownError.left()
        }
    }
}
