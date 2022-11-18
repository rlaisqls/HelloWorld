package com.example.helloworld.thirdparty.feign.exception

import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.error.GlobalErrorCode

object OtherUnAuthorizedException : BusinessException(GlobalErrorCode.OTHER_UNAUTHORIZED)