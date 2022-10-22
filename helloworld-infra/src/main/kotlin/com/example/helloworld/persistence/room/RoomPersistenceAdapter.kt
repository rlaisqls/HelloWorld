package com.example.helloworld.persistence.room

import com.example.helloworld.domain.room.exception.FulledRoomException
import com.example.helloworld.domain.room.model.Room
import com.example.helloworld.domain.room.model.RoomUser
import com.example.helloworld.domain.room.spi.RoomPort
import com.example.helloworld.domain.user.model.User
import com.example.helloworld.persistence.room.mapper.RoomMapper
import com.example.helloworld.persistence.room.mapper.RoomUserMapper
import com.example.helloworld.persistence.room.repository.RoomJpaRepository
import com.example.helloworld.persistence.room.repository.RoomUserJpaRepository
import com.example.helloworld.persistence.user.mapper.UserMapper
import org.springframework.stereotype.Component

@Component
class RoomPersistenceAdapter(
    private val roomRepository: RoomJpaRepository,
    private val roomUserRepository: RoomUserJpaRepository,
    private val roomMapper: RoomMapper,
    private val roomUserMapper: RoomUserMapper,
    private val userMapper: UserMapper
) : RoomPort {

    override fun saveRoom(room: Room) = roomMapper.toDomain(
        roomRepository.save(
            roomMapper.toEntity(room)
        )
    )!!

    override fun roomUserExists(room: Room, user: User): Boolean {

        val roomEntity = roomMapper.toEntity(room)
        val userEntity = userMapper.toEntity(user)

        return roomUserRepository.existsByRoomAndUser(roomEntity, userEntity)
    }

    override fun isFulledRoom(room: Room): Boolean {
        val roomEntity = roomRepository.queryRoomById(room.id)!!
        return roomEntity.roomUsers.size == room.maxPeople
    }

    override fun addRoomUser(room: Room, user: User) {

        val roomUser = RoomUser(
            roomId = room.id,
            userId = user.id
        )

        roomUserRepository.save(
            roomUserMapper.toEntity(roomUser)
        )
    }

    override fun deleteRoomUser(room: Room, user: User) {

        val roomEntity = roomMapper.toEntity(room)

        roomEntity.roomUsers.removeIf { it.user.id == user.id }
        roomRepository.save(roomEntity)
    }

    override fun queryRoomById(roomId: Long): Room? = roomMapper.toDomain(
        roomRepository.queryRoomById(roomId)
    )

    override fun queryMyRoomList(user: User): List<Room> {

        val userEntity = userMapper.toEntity(user)

        return roomUserRepository.queryByUser(userEntity)
            .map { roomMapper.toDomain(it.room)!! }
    }

    override fun queryAllRoomList(): List<Room> {
        return roomRepository.findAll()
            .map { roomMapper.toDomain(it)!! }
    }

}