package com.example.helloworld.thirdparty.feign

import com.example.helloworld.thirdparty.feign.exception.OtherBadRequestException
import com.example.helloworld.thirdparty.feign.exception.OtherExpiredTokenException
import com.example.helloworld.thirdparty.feign.exception.OtherForbiddenException
import com.example.helloworld.thirdparty.feign.exception.OtherUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {

        if (response.status() >= 400) {
            when(response.status()) {
                401 ->
                    throw OtherUnAuthorizedException
                403 ->
                    throw OtherForbiddenException
                419 ->
                    throw OtherExpiredTokenException
                else ->
                    throw OtherBadRequestException
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }

}