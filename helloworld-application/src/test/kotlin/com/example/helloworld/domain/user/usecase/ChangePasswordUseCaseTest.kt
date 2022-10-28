package com.example.helloworld.domain.user.usecase

import com.example.helloworld.domain.room.exception.AlreadyJoinedRoomException
import com.example.helloworld.domain.user.dto.request.ChangePasswordRequest
import com.example.helloworld.domain.user.exception.PasswordMismatchException
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.CommandUserPort
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.then

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

    private val username = "rlaisqls"
    private val password = "password"
    private val newPassword = "newPassword"

    private val userStub by lazy {
        User(
            username = username,
            password = password
        )
    }

    @Test
    fun 비밀번호_변경_성공() {
        //given
        given(securityPort.getCurrentUserUsername())
            .willReturn(username)

        given(queryUserPort.queryUserByUsername(username))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password))
            .willReturn(true)

        given(securityPort.encode(newPassword))
            .willReturn(newPassword)

        val request = ChangePasswordRequest(
            oldPassword = password,
            newPassword = newPassword
        )

        println(userStub.copy())
        //when
        changePasswordUseCase.execute(request)

        //then
        then(commandUserPort).should(times(1)).saveUser(any())
    }

    @Test
    fun 비밀번호_불일치() {
        //given
        given(securityPort.getCurrentUserUsername())
            .willReturn(username)

        given(queryUserPort.queryUserByUsername(username))
            .willReturn(userStub)

        given(securityPort.checkPassword(password, userStub.password))
            .willReturn(false)

        val request = ChangePasswordRequest(
            oldPassword = password,
            newPassword = newPassword
        )

        //when & then
        assertThrows<PasswordMismatchException> {
            changePasswordUseCase.execute(request)
        }
        then(commandUserPort).should(times(0)).saveUser(userStub)
    }
}