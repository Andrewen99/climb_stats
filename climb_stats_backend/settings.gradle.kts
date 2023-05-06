rootProject.name = "climb_stats_backend"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val ktorVersion: String by settings

        kotlin("jvm") version kotlinVersion apply false
        id("io.ktor.plugin") version ktorVersion apply false
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion apply false
    }
}

include("climb_stats_ktor")
include("climb_stats_common")
include("climb_stats_repo_inmemory")
