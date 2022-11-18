package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignInRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.exception.UserNotFoundException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime


@ExtendWith(MockitoExtension::class)
internal class SignInUseCaseTest {

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var userJwtPort: UserJwtPort

    @InjectMocks
    private lateinit var signInUseCase: SignInUseCase

    private val name = "김은빈"
    private val email = "rlaisqls@gmail.com"
    private val password = "password"

    private val userStub by lazy {
        User(
            name = name,
            email = email,
            password = password
        )
    }

    private val tokenResponse by lazy {
        TokenResponse(
            accessToken = "access",
            accessTokenExp = LocalDateTime.of(2022,10,20,12,0),
            refreshToken = "refresh"
        )
    }

    @Test
    fun 로그인_성공() {
        //given
        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password!!))
            .willReturn(true)

        given(userJwtPort.generateToken(email))
            .willReturn(tokenResponse)

        val request = SignInRequest(
            email = email,
            password = password
        )

        //when
        val response = signInUseCase.execute(request)

        //then
        assertEquals(response, tokenResponse)
    }


    @Test
    fun email_존재하지않음() {
        //given
        given(queryUserPort.queryUserByEmail(email))
            .willReturn(null)

        val request = SignInRequest(
            email = email,
            password = password
        )

        //when & then
        assertThrows<UserNotFoundException> {
            signInUseCase.execute(request)
        }
    }

    @Test
    fun password_불일치() {
        //given
        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password!!))
            .willReturn(false)

        val request = SignInRequest(
            email = email,
            password = password
        )

        //when & then
        assertThrows<PasswordMismatchException> {
            signInUseCase.execute(request)
        }
    }

}