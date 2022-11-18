package com.example.helloworld.domain.auth.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.auth.exception.RefreshTokenNotFoundException
import com.example.helloworld.domain.auth.model.RefreshToken
import com.example.helloworld.domain.auth.spi.QueryRefreshTokenPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.then
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class ReissueTokenUseCaseTest {

    @Mock
    private lateinit var userJwtPort: UserJwtPort

    @Mock
    private lateinit var queryRefreshTokenPort: QueryRefreshTokenPort

    @InjectMocks
    private lateinit var reissueTokenUseCase: ReissueTokenUseCase

    private val email = "rlaisqls"
    private val refreshToken = "refreshToken"

    private val refreshTokenStub by lazy {
        RefreshToken(
            token = refreshToken,
            email = email,
            expiration = 123456789
        )
    }

    private val tokenResponseStub by lazy {
        TokenResponse(
            accessToken = "accessToken",
            accessTokenExp = LocalDateTime.now(),
            refreshToken = "accessToken"
        )
    }

    @Test
    fun 토큰_재발급_성공() {
        //given
        given(queryRefreshTokenPort.queryRefreshTokenByToken(refreshToken))
            .willReturn(refreshTokenStub)

        //when
        reissueTokenUseCase.execute(refreshToken)

        //then
        then(userJwtPort).should(times(1)).updateToken(any(), any())
    }

    @Test
    fun refresh_token_존재하지_않음() {
        //given
        given(queryRefreshTokenPort.queryRefreshTokenByToken(refreshToken))
            .willReturn(null)

        //when & then
        assertThrows<RefreshTokenNotFoundException> {
            reissueTokenUseCase.execute(refreshToken)
        }
    }
}