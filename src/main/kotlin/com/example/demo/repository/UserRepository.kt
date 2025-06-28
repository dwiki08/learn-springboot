package com.example.demo.repository

import com.example.demo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByNameContainingIgnoreCase(name: String): List<User>

    fun existsByEmail(email: String): Boolean

    // Custom query dengan JPQL
    @Query("SELECT u FROM User u WHERE u.age >= :minAge AND u.age <= :maxAge")
    fun findUsersByAgeRange(minAge: Int, maxAge: Int): List<User>

    // Native SQL query
    @Query(value = "SELECT * FROM users WHERE phone IS NOT NULL", nativeQuery = true)
    fun findUsersWithPhone(): List<User>
}