package ru.andrewbrody.plugins

import ru.andrewbrody.di.appModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(Level.DEBUG)
        modules(
            appModule(this@configureKoin)
        )
    }
}