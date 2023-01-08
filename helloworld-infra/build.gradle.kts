plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencies {

    implementation(project(":helloworld-presentation"))
    implementation(project(":helloworld-application"))
    implementation(project(":helloworld-domain"))

    implementationDependencies(Libraries.Security)
    implementationDependencies(Libraries.Cloud)
    implementationDependencies(Libraries.Database)
    implementationDependencies(Libraries.SocketIO)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}