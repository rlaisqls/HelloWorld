package com.example.helloworld.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

object JwtProperty {
    const val HEADER = "Authorization"
    const val PREFIX = "Bearer "
    const val ACCESS = "access"
    const val REFRESH = "refresh"
    const val AUTHORITY = "authority"
}