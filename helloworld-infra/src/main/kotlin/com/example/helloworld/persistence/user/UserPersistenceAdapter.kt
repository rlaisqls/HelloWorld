package com.example.helloworld.persistence.user

import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.persistence.user.mapper.UserMapper
import com.example.helloworld.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val securityPort: SecurityPort,
    private val userRepository: UserJpaRepository,
    private val userMapper: UserMapper
) : UserPort {

    override fun saveUser(user: User) = userMapper.toDomain(
        userRepository.save(
            userMapper.toEntity(user)
        )
    )!!

    override fun existsUserByUsername(username: String): Boolean {
        return userRepository.existsUserJpaEntityByUsername(username)
    }

    override fun queryUserByUsername(username: String): User? = userMapper.toDomain(
        userRepository.queryUserByUsername(username)
    )

    override fun queryUserById(id: Long): User?  = userMapper.toDomain(
        userRepository.queryUserById(id)
    )
}