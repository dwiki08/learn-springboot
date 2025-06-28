package com.example.demo.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Size(min = 6, message = "Password minimal 6 karakter")
@Pattern(regexp = ".*[0-9].*", message = "Password harus mengandung angka")
@Pattern(regexp = ".*[!@#$%^&*()].*", message = "Password harus mengandung simbol")
@NotBlank()
annotation class ValidPassword(
    val message: String = "Password tidak valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)