package com.example.helloworld.domain.user.usecase


import com.example.helloworld.domain.user.dto.request.ChangePasswordRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ChangePasswordUseCaseTest {

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var commandUserPort: CommandUserPort

    @InjectMocks
    private lateinit var changePasswordUseCase: ChangePasswordUseCase

    private val name = "김은빈"
    private val email = "rlaisqls@gmail.com"
    private val password = "password"
    private val newPassword = "newPassword"

    private val userStub by lazy {
        User(
            name = name,
            email = email,
            password = password
        )
    }

    @Test
    fun 비밀번호_변경_성공() {
        //given
        given(securityPort.getCurrentUserEmail())
            .willReturn(email)

        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password!!))
            .willReturn(true)

        given(securityPort.encode(newPassword))
            .willReturn(newPassword)

        val request = ChangePasswordRequest(
            oldPassword = password,
            newPassword = newPassword
        )

        //when & then
        Assertions.assertDoesNotThrow {
            changePasswordUseCase.execute(request)
        }
    }

    @Test
    fun 비밀번호_불일치() {
        //given
        given(securityPort.getCurrentUserEmail())
            .willReturn(email)

        given(queryUserPort.queryUserByEmail(email))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password!!))
            .willReturn(false)

        val request = ChangePasswordRequest(
            oldPassword = password,
            newPassword = newPassword
        )

        //when & then
        assertThrows<PasswordMismatchException> {
            changePasswordUseCase.execute(request)
        }
    }
}