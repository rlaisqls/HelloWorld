package com.example.helloworld.global.security.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.security.error.SecurityErrorCode


object ExpiredTokenException: BusinessException(SecurityErrorCode.EXPIRED_TOKEN)