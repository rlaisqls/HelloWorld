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

    override fun generateToken(email: String): TokenResponse {
        return TokenResponse(
            accessToken = createAccessToken(email),
            accessTokenExp = getAccessTokenExp(),
            refreshToken = createRefreshToken(email)
        )
    }

    override fun updateToken(refreshToken: String, email: String): TokenResponse {

        refreshTokenRepository.save(
            RefreshTokenEntity(
                email = email,
                token = refreshToken,
                expiration = securityProperties.refreshExp * 1000
            )
        )

        return TokenResponse(
            accessToken = createAccessToken(email),
            accessTokenExp = getAccessTokenExp(),
            refreshToken = refreshToken
        )
    }

    private fun createAccessToken(email: String): String {
        return createToken(email, JwtProperty.ACCESS, securityProperties.accessExp)
    }

    private fun getAccessTokenExp(): LocalDateTime {
        return LocalDateTime.now().plusSeconds(securityProperties.accessExp)
    }

    private fun createRefreshToken(email: String): String {
        val token = createToken(email, JwtProperty.REFRESH, securityProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshTokenEntity(
                email = email,
                token = token,
                expiration = securityProperties.refreshExp * 1000
            )
        )
        return token
    }

    private fun createToken(email: String, jwtType: String, exp: Long): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(securityProperties.secretKey.toByteArray()), SignatureAlgorithm.HS256)
            .setSubject(email)
            .setHeaderParam(Header.JWT_TYPE, jwtType)
            .setId(email)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

}