package com.example.helloworld.persistence.user.repository

import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


interface UserJpaRepository : CrudRepository<UserJpaEntity, Long> {

    fun existsUserJpaEntityByUsername(username: String): Boolean

    fun queryUserById(id: Long): UserJpaEntity?

    fun queryUserByUsername(username: String): UserJpaEntity?

}