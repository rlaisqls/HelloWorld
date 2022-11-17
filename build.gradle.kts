plugins {
    kotlin("jvm") version "1.6.21"
}

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = "1.6.21"
    }

    apply {
        plugin("org.jetbrains.kotlin.kapt")
        version = "1.7.10"
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")
        implementation(Dependencies.KOTLIN_REFLECT)
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
    }

}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
        }

        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}