package com.example.helloworld.global.annotation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Transactional
@Component
annotation class ReadOnlyUseCase()