package com.example.helloworld.domain.file.exception

import com.example.helloworld.domain.file.error.FileErrorCode
import com.example.helloworld.global.error.BusinessException

object FileIOInterruptedException : BusinessException(FileErrorCode.IO_INTERRUPTED)