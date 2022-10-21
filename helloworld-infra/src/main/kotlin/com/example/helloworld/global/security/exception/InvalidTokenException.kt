package com.example.helloworld.global.security.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.security.error.SecurityErrorCode

class InvalidTokenException private constructor() : BusinessException(SecurityErrorCode.INVALID_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = InvalidTokenException()
    }
}