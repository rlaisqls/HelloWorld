package com.example.helloworld.persistence.room.repository

import com.example.helloworld.persistence.room.model.RoomJpaEntity
import org.springframework.data.repository.CrudRepository

interface RoomJpaRepository : CrudRepository<RoomJpaEntity, Long> {
    fun queryRoomById(roomId: Long): RoomJpaEntity?
}