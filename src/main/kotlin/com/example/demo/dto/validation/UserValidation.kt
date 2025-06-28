package com.example.demo.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.*
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Email(message = "Format email tidak valid")
annotation class ValidEmail(
    val message: String = "Email tidak valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Size(min = 2, max = 50, message = "Nama harus antara 2-50 karakter")
annotation class ValidName(
    val message: String = "Nama tidak valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Min(value = 17, message = "Age minimal 17 tahun")
@Max(value = 100, message = "Age maksimal 100 tahun")
annotation class ValidAge(
    val message: String = "Age tidak valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
@Pattern(
    regexp = "^\\+?[1-9]\\d{1,14}$",
    message = "Nomor telepon tidak valid"
)
annotation class ValidPhone(
    val message: String = "Age tidak valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
