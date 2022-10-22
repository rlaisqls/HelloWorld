package com.example.helloworld.persistence.room.mapper

import com.example.helloworld.domain.room.exception.FulledRoomException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.room.model.RoomJpaEntity
import com.example.helloworld.persistence.room.repository.RoomJpaRepository
import com.example.helloworld.persistence.room.repository.RoomUserJpaRepository
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.stereotype.Component

@Component
class RoomMapper(
    private val roomUserMapper: RoomUserMapper
) : GenericMapper<RoomJpaEntity, Room> {

    override fun toDomain(entity: RoomJpaEntity?): Room? = entity?.let {

        Room(
            id = it.id,
            roomName = it.roomName,
            maxPeople = it.maxPeople,
            roomUsers = entity.roomUsers
                .map { roomUser -> roomUserMapper.toDomain(roomUser)!! }
        )
    }

    override fun toEntity(domain: Room): RoomJpaEntity = RoomJpaEntity(

        id = domain.id,
        roomName = domain.roomName,
        maxPeople = domain.maxPeople,
        roomUsers = domain.roomUsers
            .map { roomUserMapper.toEntity(it) }
            .toMutableList()
    )

}