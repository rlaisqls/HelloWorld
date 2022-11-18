package com.example.helloworld.thirdparty.feign.client

import com.example.helloworld.thirdparty.feign.dto.response.GoogleInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "GoogleInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleInfo {

    @GetMapping("?alt=json&access_token={ACCESS_TOKEN}")
    fun googleInfo(@PathVariable("ACCESS_TOKEN") accessToken: String): GoogleInfoResponse

}