package com.example.helloworld.persistence.room.mapper

import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.room.model.RoomJpaEntity
import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.stereotype.Component

@Component
class RoomMapper: GenericMapper<RoomJpaEntity, Room> {

    override fun toDomain(entity: RoomJpaEntity?): Room? {

        return entity?.let {
            Room(
                id = it.id,
                roomName = it.roomName,
                maxPeople = it.maxPeople
            )
        }
    }

    override fun toEntity(domain: Room): RoomJpaEntity {

        return RoomJpaEntity(
            id = domain.id,
            roomName = domain.roomName,
            maxPeople = domain.maxPeople
        )
    }
}