package com.example.helloworld.socket.mapper.annotation

import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@RestController
annotation class SocketEvent(
    val event: String,
    val requestCls: KClass<*> = Void::class
)