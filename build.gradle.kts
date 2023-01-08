plugins {
    kotlin("jvm") version PluginVersions.JVM_VERSION
}

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = PluginVersions.JVM_VERSION
    }

    apply {
        plugin("org.jetbrains.kotlin.kapt")
        version = PluginVersions.KAPT_VERSION
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