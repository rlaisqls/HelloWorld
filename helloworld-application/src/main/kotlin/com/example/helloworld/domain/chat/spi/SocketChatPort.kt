package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.user.model.User

interface SocketChatPort {

    fun sendChat(roomId: Long, chat: Chat, username: String)

}