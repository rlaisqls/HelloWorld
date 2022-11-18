package com.example.helloworld.domain.file.exception

import com.example.helloworld.domain.file.error.FileErrorCode
import com.example.helloworld.global.error.BusinessException

object FilePathNotFoundException : BusinessException(FileErrorCode.FILE_PATH_NOT_FOUND)