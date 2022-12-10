package com.example.helloworld.thirdparty.socket


import com.corundumstudio.socketio.SocketConfig
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner
import com.example.helloworld.thirdparty.socket.exception.SocketExceptionListener
import com.example.helloworld.thirdparty.socket.mapper.SocketIOAdapterMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebSocketIOConfig(
    private val socketIOAdapterMapper: SocketIOAdapterMapper
) {

    @Bean
    fun socketIOServer(): SocketIOServer {

        val socketConfig = SocketConfig()
        socketConfig.isReuseAddress = true

        val configuration = com.corundumstudio.socketio.Configuration()
        configuration.port = 8081
        configuration.origin = "*"
        configuration.socketConfig = socketConfig
        configuration.exceptionListener = SocketExceptionListener()

        val socketServer = SocketIOServer(configuration)
        socketIOAdapterMapper.addListeners(socketServer)

        return socketServer
    }

    @Bean
    fun springAnnotationScanner(socketIOServer: SocketIOServer): SpringAnnotationScanner {
        return SpringAnnotationScanner(socketIOServer)
    }

}