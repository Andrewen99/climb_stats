package andrewbrody.ru.plugins

import andrewbrody.ru.di.appModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(Level.DEBUG)
        modules(
            appModule
        )
    }
}