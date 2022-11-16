package com.example.helloworld.domain.file.usecase

import com.example.helloworld.domain.file.exception.FileInvalidExtensionException
import com.example.helloworld.domain.file.spi.UploadFilePort
import com.example.helloworld.global.annotation.UseCase
import java.io.File

@UseCase
class UploadImageUseCase(
    private val uploadFilePort: UploadFilePort
) {

    fun execute(file: File): String {

        if (!isCorrectExtension(file)) {
            file.delete()
            throw FileInvalidExtensionException
        }

        return uploadFilePort.upload(file)
    }

    fun execute(files: List<File>): List<String> {

        files.forEach {
            if (!isCorrectExtension(it)) {
                files.deleteAll()
                throw FileInvalidExtensionException
            }
        }

        return uploadFilePort.upload(files)
    }

    private fun isCorrectExtension(file: File) = when(file.extension) {
        "jpg", "jpeg", "png" -> true
        else -> false
    }

    private fun List<File>.deleteAll() = this.forEach(File::delete)

}