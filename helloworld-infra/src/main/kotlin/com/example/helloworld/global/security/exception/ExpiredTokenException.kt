package com.example.helloworld.global.security.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.security.error.SecurityErrorCode


class ExpiredTokenException private constructor() : BusinessException(SecurityErrorCode.EXPIRED_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}