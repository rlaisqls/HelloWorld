package com.example.helloworld.persistence.user.model

import com.example.helloworld.persistence.BaseEntity
import javax.persistence.*


@Entity
@Table(name = "user")
class UserJpaEntity(

    @Column(name = "user_id", nullable = false)
    override val id: Long,

    @Column(nullable = false, unique = true, length = 15)
    val username: String,

    @Column(nullable = false, length = 60)
    var password: String

): BaseEntity(id)