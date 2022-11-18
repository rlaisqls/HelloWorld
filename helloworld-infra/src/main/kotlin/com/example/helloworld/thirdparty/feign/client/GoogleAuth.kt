package com.example.helloworld.thirdparty.feign.client

import com.example.helloworld.thirdparty.feign.dto.request.GoogleCodeRequest
import com.example.helloworld.thirdparty.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping


@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
interface GoogleAuth {

    @PostMapping
    fun googleAuth(googleCodeRequest: GoogleCodeRequest): TokenResponse

}