package com.example.helloworld.persistence.user.repository

import com.example.helloworld.persistence.user.model.UserJpaEntity
import org.springframework.data.repository.CrudRepository


interface UserJpaRepository : CrudRepository<UserJpaEntity, Long> {

    fun existsUserJpaEntityByEmail(email: String): Boolean

    fun queryUserById(id: Long): UserJpaEntity?

    fun queryUserByEmail(email: String): UserJpaEntity?

}