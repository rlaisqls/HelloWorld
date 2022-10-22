package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.room.model.Room
import java.time.LocalDateTime

interface QueryChatPort {

    fun queryChatByRoom(room: Room, dateTime: LocalDateTime): List<Chat>

}