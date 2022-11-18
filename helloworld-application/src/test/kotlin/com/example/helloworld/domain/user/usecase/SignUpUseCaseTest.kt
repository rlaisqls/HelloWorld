package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignUpRequest
import com.example.helloworld.domain.user.exception.UserAlreadyExistException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserJwtPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class SignUpUseCaseTest {

    @Mock
    private lateinit var userJwtPort: UserJwtPort

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var commandUserPort: CommandUserPort

    @InjectMocks
    private lateinit var signUpUseCase: SignUpUseCase

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
    fun 회원가입_성공() {
        //given
        BDDMockito.given(queryUserPort.existsUserByEmail(email))
            .willReturn(false)

        BDDMockito.given(securityPort.encode(password))
            .willReturn(password)

        BDDMockito.given(commandUserPort.saveUser(any()))
            .willReturn(userStub)

        BDDMockito.given(userJwtPort.generateToken(userStub.email))
            .willReturn(tokenResponse)

        val request = SignUpRequest(
            name = name,
            email = email,
            password = password
        )

        //when
        val response = signUpUseCase.execute(request)

        //then
        Assertions.assertEquals(response, tokenResponse)
    }

    @Test
    fun email_중복() {
        //given
        BDDMockito.given(queryUserPort.existsUserByEmail(email))
            .willReturn(true)

        val request = SignUpRequest(
            name = name,
            email = email,
            password = password
        )

        //when & then
        assertThrows<UserAlreadyExistException> {
            signUpUseCase.execute(request)
        }
    }
}