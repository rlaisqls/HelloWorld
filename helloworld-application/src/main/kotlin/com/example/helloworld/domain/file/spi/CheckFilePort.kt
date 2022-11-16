package com.example.helloworld.domain.file.spi

interface CheckFilePort {

    fun existsPath(path: String): Boolean

}