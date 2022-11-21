package com.example.helloworld.persistence.room.mapper

import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.room.model.RoomJpaEntity
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

    override fun toEntity(domain: Room): RoomJpaEntity = domain.let {
        RoomJpaEntity(
            id = it.id,
            roomName = it.roomName,
            maxPeople = it.maxPeople,
            roomUsers = it.roomUsers
                .map { ru -> roomUserMapper.toEntity(ru) }
                .toMutableList()
        )
    }
}