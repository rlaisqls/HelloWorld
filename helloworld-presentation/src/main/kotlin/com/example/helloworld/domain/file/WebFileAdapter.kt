package com.example.helloworld.domain.file

import com.example.helloworld.domain.file.dto.response.UploadImageListWebResponse
import com.example.helloworld.domain.file.dto.response.UploadImageWebResponse
import com.example.helloworld.domain.file.usecase.UploadImageUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@RestController
@RequestMapping("/files")
class WebFileAdapter (
    private val uploadImageUseCase: UploadImageUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun uploadSingleImage(file: MultipartFile): UploadImageWebResponse {
        return UploadImageWebResponse(
            uploadImageUseCase.execute(file.let(transferFile))
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun uploadMultipleImage(files: List<MultipartFile>): UploadImageListWebResponse {
        return UploadImageListWebResponse(
            uploadImageUseCase.execute(files.map(transferFile))
        )
    }

    private val transferFile = { multipartFile: MultipartFile ->
        File("${UUID.randomUUID()}@${multipartFile.originalFilename}").let {
            FileOutputStream(it).run {
                this.write(multipartFile.bytes)
                this.close()
            }
            it
        }
    }

}