package com.example.helloworld.domain.user.exception

import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

object PasswordMismatchException: BusinessException(UserErrorCode.PASSWORD_MISMATCH)