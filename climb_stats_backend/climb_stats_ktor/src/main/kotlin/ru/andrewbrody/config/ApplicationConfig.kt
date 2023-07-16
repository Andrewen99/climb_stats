package ru.andrewbrody.config

import io.ktor.server.application.*
import io.ktor.server.engine.*

data class ApplicationConfig(
    val repoType: RepoType
)

fun createApplicationConfig(env: ApplicationEnvironment) =
    ApplicationConfig(repoType = RepoType.fromString(env.config.propertyOrNull("repo.type")?.getString()))

