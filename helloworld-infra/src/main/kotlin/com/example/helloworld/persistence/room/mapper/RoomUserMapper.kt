package com.example.helloworld.persistence.room.mapper

import com.example.helloworld.domain.room.model.RoomUser
import com.example.helloworld.persistence.GenericMapper
import com.example.helloworld.persistence.room.model.RoomUserJpaEntity
import com.example.helloworld.persistence.room.repository.RoomJpaRepository
import com.example.helloworld.persistence.room.repository.RoomUserJpaRepository
import com.example.helloworld.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class RoomUserMapper(
    private val roomRepository: RoomJpaRepository,
    private val userRepository: UserJpaRepository
) : GenericMapper<RoomUserJpaEntity, RoomUser> {

    override fun toDomain(entity: RoomUserJpaEntity?): RoomUser? {

        return entity?.let {
            RoomUser(
                id = it.id,
                roomId = it.room.id,
                userId = it.user.id
            )
        }
    }

    override fun toEntity(domain: RoomUser): RoomUserJpaEntity {

        val room = roomRepository.queryRoomById(domain.roomId)!!
        val user = userRepository.queryUserById(domain.userId)!!

        return RoomUserJpaEntity(
            id = domain.id,
            room = room,
            user = user
        )
    }
}