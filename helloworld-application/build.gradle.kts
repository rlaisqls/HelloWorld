plugins {
    kotlin("plugin.allopen") version "1.6.21"
}

dependencies {
    implementation(project(":helloworld-domain"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

allOpen {
    annotation("com.example.helloworld.global.annotation.ReadOnlyUseCase")
    annotation("com.example.helloworld.global.annotation.UseCase")
}