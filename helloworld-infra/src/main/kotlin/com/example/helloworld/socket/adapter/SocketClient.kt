package com.example.helloworld.socket.adapter

import com.corundumstudio.socketio.AckCallback

interface SocketClient {
    fun getByKey(key: String): String?
    fun joinRoom(room: String?)
    fun leaveRoom(room: String?)
}