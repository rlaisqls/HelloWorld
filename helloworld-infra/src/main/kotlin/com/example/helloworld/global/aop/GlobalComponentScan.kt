package com.example.helloworld.global.aop

import com.example.helloworld.global.annotation.ReadOnlyUseCase
import com.example.helloworld.global.annotation.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["com.example.helloworld"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class,
                ReadOnlyUseCase::class
            ]
        )
    ]
)
class GlobalComponentScan