package com.example.helloworld.global.error

open class BusinessException(
    val errorProperty: ErrorProperty
): RuntimeException()