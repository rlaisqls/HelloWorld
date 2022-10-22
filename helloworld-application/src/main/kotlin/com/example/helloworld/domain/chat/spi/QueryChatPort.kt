package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.room.model.Room

interface QueryChatPort {

    fun queryChatByRoom(room: Room, page: Int): List<Chat>

}