import ImplementationType.*
import org.gradle.api.artifacts.dsl.DependencyHandler

enum class ImplementationType(val originalName: String) {
    IMPLEMENTATION("implementation"),
    TEST_IMPLEMENTATION("testImplementation"),
    KAPT("kapt"),
    RUNTIME_ONLY("runtimeOnly")
}

interface Libraries {
    fun dependencies(): List<Pair<String, ImplementationType>>

    object Kotlin : Libraries {
        override fun dependencies() = listOf(
            "org.jetbrains.kotlin:kotlin-reflect" to IMPLEMENTATION,
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8" to IMPLEMENTATION
        )
    }

    object Jackson : Libraries {
        override fun dependencies() = listOf(
            "com.fasterxml.jackson.module:jackson-module-kotlin" to IMPLEMENTATION
        )
    }

    object Web : Libraries {
        override fun dependencies() = listOf(
            "org.springframework.boot:spring-boot-starter-web" to IMPLEMENTATION,
            "org.springframework.boot:spring-boot-starter-validation" to IMPLEMENTATION
        )
    }

    object Cloud : Libraries {
        override fun dependencies() = listOf(
            "org.springframework.cloud:spring-cloud-starter-aws" to IMPLEMENTATION,
            "org.springframework.cloud:spring-cloud-starter-openfeign" to IMPLEMENTATION
        )
    }

    object Security : Libraries {
        override fun dependencies() = listOf(
            "org.springframework.boot:spring-boot-starter-security" to IMPLEMENTATION,
            "io.jsonwebtoken:jjwt" to IMPLEMENTATION
        )
    }

    object SocketIO : Libraries {
        override fun dependencies() = listOf(
            "com.corundumstudio.socketio:netty-socketio:${DependencyVersions.SOCKET_IO_VERSION}" to IMPLEMENTATION
        )
    }

    object Database : Libraries {
        override fun dependencies() = listOf(
            "org.springframework.boot:spring-boot-starter-data-jpa" to IMPLEMENTATION,
            "org.springframework.boot:spring-boot-starter-cache" to IMPLEMENTATION,
            "mysql:mysql-connector-java" to RUNTIME_ONLY,
            "org.springframework.boot:spring-boot-starter-data-redis" to RUNTIME_ONLY
        )
    }

    object Querydsl : Libraries {
        override fun dependencies() = listOf(
            "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL_VERSION}" to IMPLEMENTATION,
            "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL_VERSION}:jpa" to KAPT
        )
    }

    object Cache : Libraries {
        override fun dependencies() = listOf(
            "net.sf.ehcache:ehcache:2.10.9.2" to IMPLEMENTATION
        )
    }

    object Test : Libraries {
        override fun dependencies() = listOf(
            "org.springframework.boot:spring-boot-starter-test:2.7.5" to TEST_IMPLEMENTATION,
            "org.mockito.kotlin:mockito-kotlin:4.0.0" to TEST_IMPLEMENTATION
        //"io.kotest:kotest-runner-junit5:${DependencyVersions.KOTEST_VERSION}" to TEST_IMPLEMENTATION,
            //"io.mockk:mockk:${DependencyVersions.MOCKK_VERSION}" to TEST_IMPLEMENTATION
        )
    }
}