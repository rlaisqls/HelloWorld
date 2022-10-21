package com.example.helloworld.persistence.room.repository

import com.example.helloworld.persistence.room.model.RoomJpaEntity
import com.example.helloworld.persistence.room.model.RoomUserJpaEntity
import com.example.helloworld.persistence.room.model.RoomUserJpaId
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository

interface RoomUserJpaRepository : CrudRepository<RoomUserJpaEntity, RoomUserJpaId> {

    @EntityGraph(attributePaths = ["room"])
    fun queryByUser(user: UserJpaEntity): List<RoomUserJpaEntity>

    fun queryByRoomAndUser(room: RoomJpaEntity, user: UserJpaEntity): RoomUserJpaEntity
}