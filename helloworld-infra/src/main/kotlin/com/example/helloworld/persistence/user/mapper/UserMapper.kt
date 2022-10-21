package com.example.helloworld.persistence.user.mapper

import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.stereotype.Component

@Component
class UserMapper: GenericMapper<UserJpaEntity, User> {

    override fun toDomain(entity: UserJpaEntity?): User? {

        return entity?.let {
            User(
                id = it.id,
                username = it.username,
                password = it.password
            )
        }
    }

    override fun toEntity(domain: User): UserJpaEntity {

        return UserJpaEntity(
            id = domain.id,
            username = domain.username,
            password = domain.password
        )
    }
}