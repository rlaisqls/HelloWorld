package com.example.helloworld.persistence.chat

import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.chat.spi.ChatPort
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.persistence.chat.mapper.ChatMapper
import com.example.helloworld.persistence.chat.repository.ChatJpaRepository
import com.example.helloworld.persistence.room.mapper.RoomMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

@Component
class ChatPersistenceAdapter(
    private val chatRepository: ChatJpaRepository,
    private val chatMapper: ChatMapper,
    private val roomMapper: RoomMapper
) : ChatPort {

    override fun queryChatByRoom(room: Room, dateTime: LocalDateTime): List<Chat> =
        chatRepository.queryTop50ByRoomAndSentAtBefore(
            room = roomMapper.toEntity(room),
            sentAt = dateTime
        ).map { chatMapper.toDomain(it)!! }

    override fun save(chat: Chat): Chat = chatMapper.toDomain(
        chatRepository.save(
            chatMapper.toEntity(chat)
        )
    )!!
}