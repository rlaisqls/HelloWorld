package com.example.helloworld.thirdparty.feign.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.error.GlobalErrorCode

object OtherExpiredTokenException: BusinessException(GlobalErrorCode.OTHER_EXPIRED_TOKEN)