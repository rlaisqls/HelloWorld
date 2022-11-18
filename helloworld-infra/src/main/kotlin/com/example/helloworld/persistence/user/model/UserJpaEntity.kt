package com.example.helloworld.persistence.user.model

import com.example.helloworld.persistence.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "user")
class UserJpaEntity(

    @Column(name = "user_id", nullable = false)
    override val id: Long,

    @Column(nullable = false, length = 15)
    val name: String,

    @Column(nullable = false, length = 255)
    val profileImageUrl: String,

    @Column(nullable = false, unique = true, length = 40)
    val email: String,

    @Column(nullable = true, length = 60)
    var password: String?

): BaseEntity(id)