package com.example.helloworld.socket.mapper

import com.corundumstudio.socketio.SocketIOServer
import com.example.helloworld.socket.client.CustomSocketIOClient
import com.example.helloworld.global.socket.SocketClient
import com.example.helloworld.global.annotation.SocketEvent
import com.example.helloworld.global.annotation.WebSocketAdapter
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Method


@Component
class SocketIOAdapterMapper(
    private val beanFactory: ConfigurableListableBeanFactory,
) {

    fun addListeners(socketIOServer: SocketIOServer) {
        val classes: List<Class<*>> = beanFactory
            .getBeansWithAnnotation(WebSocketAdapter::class.java).values
            .map { obj: Any -> obj.javaClass }

        for (cls in classes) {
            val methods: List<Method> = findSocketMappingAnnotatedMethods(cls)
            addSocketServerEventListener(socketIOServer, cls, methods)
        }
    }

    private fun addSocketServerEventListener(
        socketIOServer: SocketIOServer,
        controller: Class<*>,
        methods: List<Method>
    ) {
        for (method in methods) {
            val socketEvent: SocketEvent = method.getAnnotation(SocketEvent::class.java)

            val event = socketEvent.event
            val dtoClass = socketEvent.requestCls

            socketIOServer.addEventListener(event, dtoClass.java) { client, data, _ ->

                val args: MutableList<Any> = ArrayList()
                for (params in method.parameterTypes) {
                    if (params == SocketClient::class.java) {
                        args.add(CustomSocketIOClient(client))
                    } else if (params == dtoClass.java) {
                        args.add(data)
                    }
                }
                method.invoke(beanFactory.getBean(controller), *args.toTypedArray())
            }
        }
    }

    private fun findSocketMappingAnnotatedMethods(cls: Class<*>): List<Method> {
        return cls.methods
            .filter { it.getAnnotation(SocketEvent::class.java) != null }
    }
}