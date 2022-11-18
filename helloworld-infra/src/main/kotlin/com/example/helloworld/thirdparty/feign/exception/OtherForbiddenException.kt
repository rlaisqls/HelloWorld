package com.example.helloworld.thirdparty.feign.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.error.GlobalErrorCode

object OtherForbiddenException: BusinessException(GlobalErrorCode.OTHER_FORBIDDEN)