package com.example.helloworld.socket.exception

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.listener.ExceptionListener
import com.example.helloworld.global.error.BusinessException
import com.example.helloworld.global.error.GlobalErrorCode
import com.example.helloworld.global.error.dto.ErrorResponse
import com.example.helloworld.global.property.SocketProperty
import io.netty.channel.ChannelHandlerContext

class SocketExceptionListener : ExceptionListener {

    override fun onEventException(e: Exception, args: List<Any?>?, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onDisconnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onConnectException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
        client.disconnect()
    }

    override fun onPingException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun onPongException(e: Exception, client: SocketIOClient) {
        runExceptionHandling(e, client)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, e: Throwable?): Boolean {
        return false
    }

    private fun runExceptionHandling(e: Exception, client: SocketIOClient) {

        val errorCode = if (e.cause is BusinessException) {
            (e.cause as BusinessException?)!!.errorProperty
        } else {
            GlobalErrorCode.INTERNAL_SERVER_ERROR
        }

        val message = ErrorResponse(
            status = errorCode.status(),
            message = errorCode.message()
        )

        client.sendEvent(SocketProperty.ERROR, message)
    }
}