package com.example.helloworld.domain.user.exception

import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

class UserAlreadyExistException : BusinessException(UserErrorCode.USER_ALREADY_EXIST) {

    companion object {
        @JvmField
        val EXCEPTION = UserAlreadyExistException()
    }

}