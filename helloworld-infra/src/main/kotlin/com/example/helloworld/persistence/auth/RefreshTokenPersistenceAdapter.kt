package com.example.helloworld.persistence.auth

import com.example.helloworld.domain.auth.spi.RefreshTokenPort
import com.example.helloworld.persistence.auth.mapper.RefreshTokenMapper
import com.example.helloworld.persistence.auth.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {

    override fun queryRefreshTokenByToken(token: String) = refreshTokenMapper.toDomain(
        refreshTokenRepository.findByIdOrNull(token)
    )

}