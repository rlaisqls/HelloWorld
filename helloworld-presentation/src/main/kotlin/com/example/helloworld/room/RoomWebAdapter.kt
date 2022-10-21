package com.example.helloworld.room

import com.example.helloworld.domain.room.dto.request.CreateRoomRequest
import com.example.helloworld.room.dto.request.CreateRoomWebRequest
import com.example.helloworld.domain.room.usecase.CreateRoomUseCase
import com.example.helloworld.domain.room.usecase.JoinRoomUseCase
import com.example.helloworld.domain.room.usecase.LeaveRoomUseCase
import com.example.helloworld.domain.room.usecase.QueryAllRoomListUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/rooms")
class RoomWebAdapter (
    private val createRoomUseCase: CreateRoomUseCase,
    private val joinRoomUseCase: JoinRoomUseCase,
    private val leaveRoomUseCase: LeaveRoomUseCase,
    private val queryAllRoomListUseCase: QueryAllRoomListUseCase,
    private val queryMyRoomListUseCase: QueryAllRoomListUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createRoom(@Valid @RequestBody request: CreateRoomWebRequest) {
        createRoomUseCase.execute(
            CreateRoomRequest(
                roomName = request.roomName,
                maxPeople = request.maxPeople
            )
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/join/{room-id}")
    fun joinRoom(@PathVariable("room-id") roomId: Long) {
        joinRoomUseCase.execute(roomId)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/leave/{room-id}")
    fun leaveRoom(@PathVariable("room-id") roomId: Long) {
        leaveRoomUseCase.execute(roomId)
    }

    @GetMapping("/list")
    fun queryAllRoomList() {
        queryAllRoomListUseCase.execute()
    }

    @GetMapping("/list/my")
    fun queryMyRoomList() {
        queryMyRoomListUseCase.execute()
    }
}