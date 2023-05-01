package andrewbrody.ru.plugins

import andrewbrody.ru.service.TestService
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
