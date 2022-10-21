package com.example.helloworld.domain.auth.exception

import com.example.helloworld.domain.auth.error.AuthErrorCode
import com.example.helloworld.global.error.BusinessException

class RefreshTokenNotFoundException private constructor(): BusinessException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = RefreshTokenNotFoundException()
    }

}