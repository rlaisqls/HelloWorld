package com.example.helloworld.persistence.room.model

import com.example.helloworld.persistence.BaseEntity
import com.example.helloworld.persistence.user.model.UserJpaEntity
import javax.persistence.*

@Entity
@Table(name = "room_user")
class RoomUserJpaEntity(

    @Column(name = "room_user_id", nullable = false)
    override val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    val room: RoomJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseEntity(id)