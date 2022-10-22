package com.example.helloworld.domain.room.usecase

import com.example.helloworld.domain.room.dto.response.QueryRoomListResponse
import com.example.helloworld.domain.room.spi.QueryRoomPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.domain.user.spi.UserPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class QueryMyRoomListUseCaseTest {

    @Mock
    private lateinit var userPort: UserPort

    @Mock
    private lateinit var queryRoomPort: QueryRoomPort

    @InjectMocks
    private lateinit var queryMyRoomListUseCase: QueryMyRoomListUseCase

    private val username = "rlaisqls"
    private val password = "password"

    private val userStub by lazy {
        User(
            username = username,
            password = password
        )
}

    @Test
    fun 내_채팅방_조회_성공() {
        //given
        BDDMockito.given(userPort.getCurrentUser())
            .willReturn(userStub)

        BDDMockito.given(queryRoomPort.queryMyRoomList(userStub))
            .willReturn(emptyList())

        //when
        val response = queryMyRoomListUseCase.execute()

        //then
        assertEquals(response.roomList, emptyList<QueryRoomListResponse>())
    }

}