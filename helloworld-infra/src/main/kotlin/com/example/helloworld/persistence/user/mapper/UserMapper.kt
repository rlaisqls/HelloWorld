package com.example.helloworld.persistence.user.mapper

import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.stereotype.Component

@Component
class UserMapper : GenericMapper<UserJpaEntity, User> {

    override fun toDomain(entity: UserJpaEntity?): User? = entity?.let {
        User(
            id = it.id,
            name = it.name,
            profileImageUrl = it.profileImageUrl,
            email = it.email,
            password = it.password
        )
    }

    override fun toEntity(domain: User): UserJpaEntity = domain.let {
        UserJpaEntity(
            id = it.id,
            name = it.name,
            profileImageUrl = it.profileImageUrl,
            email = it.email,
            password = it.password
        )
    }

}