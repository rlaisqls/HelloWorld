plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("plugin.allopen") version "1.6.21"
}

dependencies {
    implementation(project(":helloworld-domain"))

    implementation("org.springframework:spring-context:5.3.23")
    implementation("org.springframework:spring-tx:5.3.23")
}

allOpen {
    annotation("com.example.helloworld.global.annotation.ReadOnlyUseCase")
    annotation("com.example.helloworld.global.annotation.UseCase")
}