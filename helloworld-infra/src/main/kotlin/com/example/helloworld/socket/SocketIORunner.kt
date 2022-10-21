package com.example.helloworld.socket

import com.corundumstudio.socketio.SocketIOServer

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class SocketIORunner(
    private val socketIOServer: SocketIOServer
) : CommandLineRunner {

    override fun run(args: Array<String>) {
        socketIOServer.start()
    }
}