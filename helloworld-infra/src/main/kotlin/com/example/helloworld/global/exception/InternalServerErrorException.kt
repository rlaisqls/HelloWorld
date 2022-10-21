package com.example.helloworld.global.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.error.GlobalErrorCode

class InternalServerErrorException private constructor() : BusinessException(GlobalErrorCode.INTERNAL_SERVER_ERROR) {

    companion object {
        @JvmField
        val EXCEPTION = InternalServerErrorException()
    }
}