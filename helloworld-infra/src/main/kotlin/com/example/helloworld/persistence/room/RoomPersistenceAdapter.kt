package com.example.helloworld.persistence.room

import com.example.helloworld.domain.room.exception.RoomNotFoundException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.model.RoomUser
import com.example.helloworld.domain.room.spi.RoomPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.room.mapper.RoomMapper
import com.example.helloworld.persistence.room.mapper.RoomUserMapper
import com.example.helloworld.persistence.room.model.RoomJpaEntity
import com.example.helloworld.persistence.room.model.RoomUserJpaEntity
import com.example.helloworld.persistence.room.model.RoomUserJpaId
import com.example.helloworld.persistence.room.repository.RoomJpaRepository
import com.example.helloworld.persistence.room.repository.RoomUserJpaRepository
import com.example.helloworld.persistence.user.mapper.UserMapper
import com.example.helloworld.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class RoomPersistenceAdapter(
    private val roomRepository: RoomJpaRepository,
    private val roomUserRepository: RoomUserJpaRepository,
    private val roomMapper: RoomMapper,
    private val userMapper: UserMapper
) : RoomPort {

    override fun save(room: Room) = roomMapper.toDomain(
        roomRepository.save(
            roomMapper.toEntity(room)
        )
    )!!

    override fun roomUserExists(room: Room, user: User): Boolean {
        return roomUserRepository
            .existsById(getRoomUserId(room, user))
    }

    override fun addRoomUser(room: Room, user: User) {
        roomUserRepository.save(
            RoomUserJpaEntity(getRoomUserId(room, user))
        )
    }

    private fun getRoomUserId(room: Room, user: User) = RoomUserJpaId(
        room = room.id,
        user = user.id
    )

    override fun deleteRoomUser(room: Room, user: User) {

        val roomUser = roomUserRepository.queryByRoomAndUser(
            room = roomMapper.toEntity(room),
            user = userMapper.toEntity(user)
        )

        roomUserRepository.delete(roomUser)
    }

    override fun queryRoomById(roomId: Long): Room? = roomMapper.toDomain(
        roomRepository.queryRoomById(roomId)
    )

    override fun queryMyRoomList(user: User): List<Room> {
        return roomUserRepository.queryByUser(userMapper.toEntity(user))
            .map { roomMapper.toDomain(it.room)!! }
    }

    override fun queryAllRoomList(): List<Room> {
        return roomRepository.findAll()
            .map { roomMapper.toDomain(it)!! }
    }

}