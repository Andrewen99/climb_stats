package ru.andrewbrody

import io.ktor.server.application.*
import ru.andrewbrody.plugins.configureKoin
import ru.andrewbrody.plugins.configureRouting
import ru.andrewbrody.plugins.configureSerialization

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureRouting()
    configureKoin()
}
