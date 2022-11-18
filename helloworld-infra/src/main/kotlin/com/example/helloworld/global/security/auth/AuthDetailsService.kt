package com.example.helloworld.global.security.auth

import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.persistence.user.model.UserJpaEntity
import com.example.helloworld.persistence.user.repository.UserJpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userRepository: UserJpaRepository
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user: UserJpaEntity = userRepository.queryUserByEmail(email) ?: throw UserNotFoundException
        return AuthDetails(user.email)
    }

}