package com.example.helloworld.persistence.room.model

import com.example.helloworld.persistence.BaseEntity
import javax.persistence.*
import javax.validation.constraints.Size


@Entity
@Table(name = "room")
class RoomJpaEntity(

    @Column(name = "room_id", nullable = false)
    override val id: Long,

    @Column(nullable = false, length = 15)
    val roomName: String,

    @Size(max = 50)
    @Column(nullable = false)
    val maxPeople: Int,

    @OneToMany(mappedBy = "room", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val roomUsers: MutableList<RoomUserJpaEntity> = ArrayList()

) : BaseEntity(id)