package com.example.helloworld.domain.auth.spi

interface QueryAuthLinkPort {
    fun queryGoogleAuthLink(): String
}