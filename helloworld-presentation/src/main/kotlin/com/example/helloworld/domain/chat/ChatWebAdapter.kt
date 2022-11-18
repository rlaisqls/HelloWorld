package com.example.helloworld.domain.chat

import com.example.helloworld.domain.chat.dto.response.QueryChatListResponse
import com.example.helloworld.domain.chat.usecase.QueryChatUseCase
import com.example.helloworld.global.annotation.WebAdapter
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@WebAdapter
@RequestMapping("/chats")
class ChatWebAdapter (
    private val queryChatUseCase: QueryChatUseCase
) {
    @GetMapping("/room/{room-id}")
    fun queryChat(@PathVariable("room-id") roomId: Long,
                  @RequestParam
                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) dateTime: LocalDateTime): QueryChatListResponse {
        return queryChatUseCase.execute(roomId, dateTime)
    }

}