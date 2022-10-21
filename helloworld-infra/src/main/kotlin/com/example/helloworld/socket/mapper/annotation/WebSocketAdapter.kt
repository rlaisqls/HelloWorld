package com.example.helloworld.socket.mapper.annotation


import org.springframework.web.bind.annotation.RestController

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@RestController
annotation class WebSocketAdapter