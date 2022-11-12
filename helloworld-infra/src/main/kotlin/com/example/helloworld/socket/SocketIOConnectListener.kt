package com.example.helloworld.socket

import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.annotation.OnConnect
import com.corundumstudio.socketio.annotation.OnDisconnect
import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.global.security.jwt.JwtParser
import com.example.helloworld.global.security.jwt.JwtProperty
import com.example.helloworld.socket.property.ClientProperty
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


@Component
class SocketIOConnectListener(
    private val jwtParser: JwtParser
) {

    companion object {
        val socketIOClientMap: ConcurrentMap<String, MutableList<SocketIOClient>> = ConcurrentHashMap()
    }

    @OnConnect
    fun onConnect(socketIOClient: SocketIOClient) {

        val token = jwtParser.resolveToken(getHeader(socketIOClient, JwtProperty.HEADER))
        val authentication = jwtParser.getAuthentication(token)

        val username = authentication.name
        val roomId = getRoomIdParam(socketIOClient)

        if(socketIOClientMap[roomId] == null) {
            socketIOClientMap[roomId] = mutableListOf(socketIOClient)
        } else {
            socketIOClientMap[roomId]!!.add(socketIOClient)
        }

        socketIOClient[ClientProperty.USER_KEY] = username
        socketIOClient[ClientProperty.ROOM_KEY] = roomId
    }

    private fun getHeader(socketIOClient: SocketIOClient, header: String): String {
        return socketIOClient.handshakeData.httpHeaders.get(header)
    }

    private fun getRoomIdParam(socketIOClient: SocketIOClient): String {
        return socketIOClient.handshakeData
            .urlParams[ClientProperty.ROOM_ID_PARAM]!![0]
            ?: throw RoomNotFoundException
    }

    @OnDisconnect
    fun onDisconnect(socketIOClient: SocketIOClient) {

        val roomId = socketIOClient.get<String>(ClientProperty.ROOM_KEY)

        socketIOClientMap[roomId]?.remove(socketIOClient)
    }

}