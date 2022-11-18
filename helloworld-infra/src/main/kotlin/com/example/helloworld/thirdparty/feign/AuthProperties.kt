package com.example.helloworld.thirdparty.feign

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "auth")
class AuthProperties (
    val baseUrl: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUrl: String
)