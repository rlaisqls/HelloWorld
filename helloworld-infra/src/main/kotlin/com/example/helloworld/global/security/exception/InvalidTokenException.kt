package com.example.helloworld.global.security.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.security.error.SecurityErrorCode

object InvalidTokenException: BusinessException(SecurityErrorCode.INVALID_TOKEN)