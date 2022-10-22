package com.example.helloworld.global.socket

interface SocketClient {
    fun getByKey(key: String): String?
    fun joinRoom(room: String?)
    fun leaveRoom(room: String?)
}