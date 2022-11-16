package com.example.helloworld.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.helloworld.domain.file.exception.FileIOInterruptedException
import com.example.helloworld.domain.file.spi.CheckFilePort
import com.example.helloworld.domain.file.spi.UploadFilePort
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException

@Component
class AwsS3Adapter(
    private val awsProperties: AwsS3Properties,
    private val amazonS3Client: AmazonS3Client
): UploadFilePort, CheckFilePort {

    override fun upload(file: File): String {

        inputS3(file)
        return getResource(file.name)
    }

    override fun upload(files: List<File>): List<String> {

        return files.map {
            inputS3(it)
            getResource(it.name)
        }
    }

    private fun inputS3(file: File) {
        try {
            val inputStream = file.inputStream()
            val objectMetadata = ObjectMetadata().apply {
                this.contentLength = file.length()
                this.contentType = Mimetypes.getInstance().getMimetype(file)
            }

            amazonS3Client.putObject(PutObjectRequest(awsProperties.bucket, file.name, inputStream, objectMetadata).withCannedAcl(
                CannedAccessControlList.PublicRead))
            file.delete()
        } catch (e: IOException) {
            throw FileIOInterruptedException
        }
    }

    private fun getResource(fileName: String): String {
        return amazonS3Client.getResourceUrl(awsProperties.bucket, fileName)
    }

    override fun existsPath(path: String): Boolean {
        return amazonS3Client.doesObjectExist(awsProperties.bucket, path.substringAfterLast('/', ""))
    }

}