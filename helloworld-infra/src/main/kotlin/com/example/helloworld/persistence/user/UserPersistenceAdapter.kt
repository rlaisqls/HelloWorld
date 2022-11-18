package com.example.helloworld.persistence.user

import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.UserPort
import com.example.helloworld.persistence.user.mapper.UserMapper
import com.example.helloworld.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserJpaRepository,
    private val userMapper: UserMapper
) : UserPort {

    override fun saveUser(user: User) = userMapper.toDomain(
        userRepository.save(
            userMapper.toEntity(user)
        )
    )!!

    override fun existsUserByEmail(email: String): Boolean {
        return userRepository.existsUserJpaEntityByEmail(email)
    }

    override fun queryUserByEmail(email: String): User? = userMapper.toDomain(
        userRepository.queryUserByEmail(email)
    )

    override fun queryUserById(id: Long): User?  = userMapper.toDomain(
        userRepository.queryUserById(id)
    )
}