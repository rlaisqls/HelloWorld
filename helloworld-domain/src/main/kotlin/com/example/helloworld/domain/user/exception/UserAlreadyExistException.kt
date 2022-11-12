package com.example.helloworld.domain.user.exception

import com.example.helloworld.domain.user.error.UserErrorCode
import com.example.helloworld.global.error.BusinessException

object UserAlreadyExistException: BusinessException(UserErrorCode.USER_ALREADY_EXIST)