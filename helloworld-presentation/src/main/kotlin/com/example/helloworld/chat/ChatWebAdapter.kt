package com.example.helloworld.chat

import com.example.helloworld.domain.chat.usecase.QueryChatUseCase
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/chats")
class ChatWebAdapter (
    private val queryChatUseCase: QueryChatUseCase
) {

    @GetMapping("/room/{room-id}")
    fun joinRoom(@PathVariable("room-id") roomId: Long, @RequestParam page: Int) {
        queryChatUseCase.execute(roomId, page)
    }

}