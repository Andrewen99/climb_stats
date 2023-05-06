package ru.andrewbrody.plugins

import ru.andrewbrody.service.TestService
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val testService by inject<TestService>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test") {
            call.respondText(testService.testFun())
        }

    }
}
