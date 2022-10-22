package com.example.helloworld.domain.auth.spi

import com.example.helloworld.domain.auth.model.RefreshToken

interface QueryRefreshTokenPort {

    fun queryRefreshTokenByToken(token: String): RefreshToken?

}