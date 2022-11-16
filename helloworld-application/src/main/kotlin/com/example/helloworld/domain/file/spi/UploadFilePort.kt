package com.example.helloworld.domain.file.spi

import java.io.File

interface UploadFilePort {

    fun upload(file: File): String
    fun upload(files: List<File>): List<String>

}