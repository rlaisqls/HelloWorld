package com.example.helloworld.persistence.chat.mapper

import com.example.helloworld.domain.chat.model.Chat
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.chat.model.ChatJpaEntity
import com.example.helloworld.persistence.room.repository.RoomJpaRepository
import com.example.helloworld.persistence.user.mapper.UserMapper
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ChatMapper(
    private val userMapper: UserMapper,
    private val roomRepository: RoomJpaRepository
) : GenericMapper<ChatJpaEntity, Chat> {

    override fun toDomain(entity: ChatJpaEntity?): Chat? = entity?.let {
        Chat(
            id = entity.id,
            roomId = it.room.id,
            username = it.username,
            message = it.message,
            sentAt = it.sentAt
        )
    }

    override fun toEntity(domain: Chat): ChatJpaEntity {

        val room = roomRepository.queryRoomById(domain.roomId)!!

        return ChatJpaEntity(
            id = domain.id,
            room = room,
            username = domain.username,
            message = domain.message,
            sentAt = domain.sentAt
        )
    }
}