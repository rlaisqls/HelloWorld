package com.example.helloworld.domain.file.error

import com.example.helloworld.global.error.ErrorProperty

enum class FileErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    INVALID_EXTENSION(400, "Invalid Extension. It must be (jpg, jpeg, png)"),

    FILE_PATH_NOT_FOUND(404, "File Path Not Found"),

    IO_INTERRUPTED(500, "IO Interrrupted");

    override fun status(): Int = status
    override fun message(): String = message
}