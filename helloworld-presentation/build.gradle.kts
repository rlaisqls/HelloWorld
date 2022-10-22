
plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    implementation(project(":helloworld-application"))
    implementation(project(":helloworld-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.getByName<Jar>("bootJar") {
    enabled = false
}