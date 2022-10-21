package com.example.helloworld.persistence.auth.mapper

import com.example.helloworld.domain.auth.model.RefreshToken
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.auth.entity.RefreshTokenEntity
import org.springframework.stereotype.Component

@Component
class RefreshTokenMapper : GenericMapper<RefreshTokenEntity, RefreshToken> {

    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? {
        return entity?.let {
            RefreshToken(
                token = it.token,
                username = it.username,
                expiration = it.expiration
            )
        }
    }

    override fun toEntity(domain: RefreshToken): RefreshTokenEntity {
        return RefreshTokenEntity(
            token = domain.token,
            username = domain.username,
            expiration = domain.expiration
        )
    }

}