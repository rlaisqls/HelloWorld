package com.example.helloworld.domain.chat.spi

import com.example.helloworld.global.socket.SocketClient

interface SocketUserPort {

    fun getCurrentUsername(socketClient: SocketClient): String

    fun getCurrentRoomId(socketClient: SocketClient): String

}