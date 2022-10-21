plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("plugin.allopen") version "1.6.21"
}

dependencies {
    implementation(project(":helloworld-domain"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-tx:5.3.22")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-inline:4.2.0")
}

allOpen {
    annotation("com.example.helloworld.global.annotation.ReadOnlyUseCase")
    annotation("com.example.helloworld.global.annotation.UseCase")
}