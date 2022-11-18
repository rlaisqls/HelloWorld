package com.example.helloworld.domain.chat.spi

import com.example.helloworld.global.socket.SocketClient

interface SocketUserPort {

    fun getCurrentemail(socketClient: SocketClient): String

    fun getCurrentRoomId(socketClient: SocketClient): String

}