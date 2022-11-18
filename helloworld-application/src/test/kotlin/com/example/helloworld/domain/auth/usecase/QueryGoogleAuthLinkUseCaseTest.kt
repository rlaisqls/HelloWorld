package com.example.helloworld.domain.auth.usecase

import com.example.helloworld.domain.auth.spi.QueryAuthLinkPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

internal class QueryGoogleAuthLinkUseCaseTest {

    @Mock
    lateinit var queryAuthLinkPort: QueryAuthLinkPort

    @InjectMocks
    lateinit var queryGoogleAuthLinkUseCase: QueryGoogleAuthLinkUseCase

    private val link = "link"

    @Test
    fun 구글_Oauth_인증링크_조회() {

        //given
        given(queryAuthLinkPort.queryGoogleAuthLink()).willReturn(link)

        //when
        val response = queryGoogleAuthLinkUseCase.execute()

        //then
        assertEquals(response.link, link)
    }

}