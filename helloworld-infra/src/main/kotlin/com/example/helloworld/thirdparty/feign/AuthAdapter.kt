package com.example.helloworld.thirdparty.feign

import com.example.helloworld.domain.auth.spi.AuthPort
import com.example.helloworld.domain.auth.spi.dto.response.AuthInfoResponse
import com.example.helloworld.thirdparty.feign.client.GoogleAuth
import com.example.helloworld.thirdparty.feign.client.GoogleInfo
import com.example.helloworld.thirdparty.feign.dto.request.GoogleCodeRequest
import org.springframework.stereotype.Component
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Component
class AuthAdapter(
    val googleAuth: GoogleAuth,
    val googleInfo: GoogleInfo,
    val authProperties: AuthProperties
) : AuthPort {

    override fun getGoogleAuthInfo(code: String): AuthInfoResponse {

        val accessToken: String = googleAuth.googleAuth(
            GoogleCodeRequest(
                code = URLDecoder.decode(code, StandardCharsets.UTF_8),
                clientId = authProperties.clientId,
                clientSecret = authProperties.clientSecret,
                redirectUri = authProperties.redirectUrl
            )
        ).accessToken

        val response = googleInfo.googleInfo(accessToken)
        return AuthInfoResponse(
            email = response.email,
            name = response.name,
            profileImageUrl = response.profileImageUrl
        )
    }

    override fun queryGoogleAuthLink(): String {
        return String.format(
            AuthProperty.GOOGLE_LINK,
            authProperties.baseUrl,
            authProperties.clientId,
            authProperties.redirectUrl
        )
    }

}