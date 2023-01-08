plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
    implementation(project(":helloworld-domain"))

    implementationDependencies(Libraries.Test)
}

allOpen {
    annotation("com.example.helloworld.global.annotation.ReadOnlyUseCase")
    annotation("com.example.helloworld.global.annotation.UseCase")
}