package com.example.helloworld.global.security

import com.example.helloworld.domain.user.spi.SecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityAdapter(
    private val passwordEncoder: PasswordEncoder
): SecurityPort {
    override fun checkPassword(password: String, encryptedPassword: String): Boolean {
        return passwordEncoder.matches(password, encryptedPassword)
    }

    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun getCurrentUserUsername(): String {
        return SecurityContextHolder.getContext().authentication.name
    }
}