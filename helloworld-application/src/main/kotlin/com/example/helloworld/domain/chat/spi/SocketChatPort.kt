package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.chat.model.Chat

interface SocketChatPort {

    fun sendChat(roomId: Long, chat: Chat)

}