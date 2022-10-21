package com.example.helloworld.domain.user.exception

import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

class PasswordMismatchException : BusinessException(UserErrorCode.PASSWORD_MISMATCH) {

    companion object {
        @JvmField
        val EXCEPTION = PasswordMismatchException()
    }

}