package com.example.helloworld.global.annotation

import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Component;

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Transactional
@Component
annotation class UseCase()