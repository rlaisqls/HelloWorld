package com.example.helloworld.persistence.chat.repository

import com.example.helloworld.persistence.chat.model.ChatJpaEntity
import com.example.helloworld.persistence.room.model.RoomJpaEntity
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.persistence.OrderBy

interface ChatJpaRepository : CrudRepository<ChatJpaEntity, Long> {

    @OrderBy(value = "sentAt DESC")
    fun queryTop50ByRoomAndSentAtBefore(room: RoomJpaEntity, sentAt: LocalDateTime): List<ChatJpaEntity>

}