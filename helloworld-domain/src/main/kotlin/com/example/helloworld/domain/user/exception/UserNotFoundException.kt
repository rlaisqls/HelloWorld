package com.example.helloworld.domain.user.exception

import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

class UserNotFoundException private constructor() : BusinessException(UserErrorCode.USER_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = UserNotFoundException()
    }

}