package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.domain.user.spi.SecurityPort
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class QueryMyRoomListUseCaseTest {

    @Mock
    private lateinit var securityPort: SecurityPort

    @Mock
    private lateinit var queryUserPort: QueryUserPort

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @InjectMocks
    private lateinit var queryMyRoomListUseCase: QueryMyRoomListUseCase

    private val email = "rlaisqls"
    private val password = "password"

    private val userStub by lazy {
        User(
            email = email,
            password = password
        )
}

    @Test
    fun 내_채팅방_조회_성공() {
        //given
        given(securityPort.getCurrentUseremail())
            .willReturn(email)

        given(queryUserPort.queryUserByemail(email))
            .willReturn(userStub)

        given(queryRoomPort.queryMyRoomList(userStub))
            .willReturn(emptyList())

        //when
        val response = queryMyRoomListUseCase.execute()

        //then
        assertEquals(response.roomList, emptyList<QueryRoomListResponse>())
    }

}