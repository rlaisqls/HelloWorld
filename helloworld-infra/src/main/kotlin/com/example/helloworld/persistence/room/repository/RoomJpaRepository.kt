package com.example.helloworld.persistence.room.repository

import com.example.helloworld.persistence.room.model.RoomJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface RoomJpaRepository : CrudRepository<RoomJpaEntity, Long> {

    fun queryRoomById(roomId: Long): RoomJpaEntity?

}