package com.example.helloworld.domain.chat.spi

import com.example.helloworld.domain.room.spi.SocketRoomPort

interface SocketPort : SocketRoomPort, SocketChatPort, SocketUserPort