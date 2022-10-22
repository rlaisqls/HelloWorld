package com.example.helloworld.global.security.jwt

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.spi.JwtPort
import com.example.helloworld.global.security.SecurityProperties
import com.example.helloworld.persistence.auth.entity.RefreshTokenEntity
import com.example.helloworld.persistence.auth.repository.RefreshTokenRepository
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class GenerateJwtAdapter(
    private val securityProperties: SecurityProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) : JwtPort {

    override fun generateToken(username: String): TokenResponse {
        return TokenResponse(
            accessToken = createAccessToken(username),
            accessTokenExp = LocalDateTime.now().plusSeconds(securityProperties.accessExp),
            refreshToken = createRefreshToken(username)
        )
    }

    fun createAccessToken(username: String): String {
        return createToken(username, JwtProperty.ACCESS, securityProperties.accessExp)
    }

    fun createRefreshToken(username: String): String {
        val token = createToken(username, JwtProperty.REFRESH, securityProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshTokenEntity(
                username = username,
                token = token,
                expiration = securityProperties.refreshExp * 1000
            )
        )
        return token
    }

    private fun createToken(username: String, jwtType: String, exp: Long): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(securityProperties.secretKey.toByteArray()), SignatureAlgorithm.HS256)
            .setSubject(username)
            .setHeaderParam(Header.JWT_TYPE, jwtType)
            .setId(username)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

}