package com.example.helloworld.global.annotation

import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SocketEvent(
    val event: String,
    val requestCls: KClass<*> = Void::class
)