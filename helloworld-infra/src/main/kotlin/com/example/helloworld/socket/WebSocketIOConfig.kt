package com.example.helloworld.socket


import com.corundumstudio.socketio.SocketConfig
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner
import com.corundumstudio.socketio.listener.ConnectListener
import com.example.helloworld.socket.exception.SocketExceptionListener
import com.example.helloworld.socket.mapper.WebSocketIOAdapterMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebSocketIOConfig(
    private val socketIOConnectListener: SocketIOConnectListener,
    private val webSocketIOAdapterMapper: WebSocketIOAdapterMapper
) {

    companion object {
        private const val PORT: Int = 8081
    }

    @Bean
    fun socketIOServer(): SocketIOServer {
        val socketConfig = SocketConfig()
        socketConfig.isReuseAddress = true

        val configuration = com.corundumstudio.socketio.Configuration()
        configuration.port = PORT
        configuration.origin = "*"
        configuration.socketConfig = socketConfig
        configuration.exceptionListener = SocketExceptionListener()

        val socketServer = SocketIOServer(configuration)
        socketServer.addConnectListener(socketIOConnectListener as ConnectListener)
        webSocketIOAdapterMapper.addListeners(socketServer)

        return socketServer
    }

    @Bean
    fun springAnnotationScanner(socketIOServer: SocketIOServer): SpringAnnotationScanner {
        return SpringAnnotationScanner(socketIOServer)
    }
}