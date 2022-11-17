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
            profileImageUrl = it.profileImageUrl,
            username = it.username,
            password = it.password
        )
    }

    override fun toEntity(domain: User): UserJpaEntity = UserJpaEntity(
        id = domain.id,
        profileImageUrl = domain.profileImageUrl,
        username = domain.username,
        password = domain.password
    )

}