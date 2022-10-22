package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.chat.model.Chat

interface CommandChatPort {

    fun save(chat: Chat): Chat

}