package com.example.helloworld.persistence.auth.repository

import com.example.helloworld.persistence.auth.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String>