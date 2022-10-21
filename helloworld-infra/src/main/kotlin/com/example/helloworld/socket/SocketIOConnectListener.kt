package com.example.helloworld.socket

import com.corundumstudio.socketio.SocketIOClient

import com.corundumstudio.socketio.annotation.OnConnect
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.user.spi.QueryUserPort
import com.example.helloworld.global.security.jwt.JwtParser
import com.example.helloworld.global.security.jwt.JwtProperty
import com.example.helloworld.socket.property.ClientProperty
import org.springframework.stereotype.Component


@Component
class SocketIOConnectListener(
    private val jwtParser: JwtParser
) {
    @OnConnect
    fun onConnect(socketIOClient: SocketIOClient) {

        val token = jwtParser.resolveToken(getHeader(socketIOClient, JwtProperty.HEADER))
        val authentication = jwtParser.getAuthentication(token)

        val username = authentication.name
        val roomId = getRoomIdParam(socketIOClient).toLong()

        socketIOClient[ClientProperty.USER_KEY] = username
        socketIOClient[ClientProperty.ROOM_KEY] = roomId
    }

    private fun getHeader(socketIOClient: SocketIOClient, header: String): String {
        return socketIOClient.handshakeData.httpHeaders.get(header)
    }

    private fun getRoomIdParam(socketIOClient: SocketIOClient): String {
        return socketIOClient.handshakeData
            .urlParams[ClientProperty.ROOM_ID_PARAM]!![0]
            ?: throw RoomNotFoundException.EXCEPTION
    }

}