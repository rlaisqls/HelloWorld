package com.example.helloworld.socket.adapter

import com.corundumstudio.socketio.SocketIOClient

class CustomSocketIOClient (
    private val socketIOClient: SocketIOClient
): SocketClient {

    override fun getByKey(key: String): String? =
        socketIOClient.get(key)

    override fun joinRoom(room: String?) {
        socketIOClient.joinRoom(room)
    }

    override fun leaveRoom(room: String?) {
        socketIOClient.leaveRoom(room)
    }

}