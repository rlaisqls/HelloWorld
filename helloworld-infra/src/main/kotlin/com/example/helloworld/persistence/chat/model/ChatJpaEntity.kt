package com.example.helloworld.persistence.chat.model

import com.example.helloworld.persistence.BaseEntity
import com.example.helloworld.persistence.room.model.RoomJpaEntity
import com.example.helloworld.persistence.user.model.UserJpaEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "chat")
class ChatJpaEntity(

    @Column(name = "chat_id", nullable = false)
    override val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    val room: RoomJpaEntity,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val message: String,

    @Column(nullable = false)
    val sentAt: LocalDateTime

): BaseEntity(id)