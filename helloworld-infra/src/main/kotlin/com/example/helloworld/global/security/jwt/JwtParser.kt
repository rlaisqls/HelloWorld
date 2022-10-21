package com.example.helloworld.global.security.jwt

import com.example.helloworld.global.security.SecurityProperties
import com.example.helloworld.global.security.auth.AuthDetailsService
import com.example.helloworld.global.security.exception.ExpiredTokenException
import com.example.helloworld.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtParser(
    private val authDetailsService: AuthDetailsService,
    private val securityProperties: SecurityProperties
){

    fun resolveToken(request: HttpServletRequest): String? {

        val bearerToken = request.getHeader(JwtProperty.HEADER)

        if (bearerToken != null && (bearerToken.startsWith(JwtProperty.HEADER))) {
            return bearerToken.substring(7)
        }
        return null
    }

    fun resolveToken(bearerToken: String?): String? {

        if (bearerToken != null && (bearerToken.startsWith(JwtProperty.HEADER))) {
            return bearerToken.substring(7)
        }
        return null
    }

    fun getAuthentication(token: String?): Authentication {

        val claims = getClaims(token)
        if (claims.header[Header.JWT_TYPE] == JwtProperty.REFRESH) {
            throw InvalidTokenException.EXCEPTION
        }

        val details = authDetailsService.loadUserByUsername(claims.body.id)
        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

    private fun getClaims(token: String?): Jws<Claims> {
        return try {
            Jwts
                .parser()
                .setSigningKey(securityProperties.secretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is ExpiredTokenException -> throw ExpiredTokenException.EXCEPTION
                else -> throw InvalidTokenException.EXCEPTION
            }
        }
    }
}