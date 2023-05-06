package ru.andrewbrody.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.BaseRq
import org.koin.ktor.ext.inject
import ru.andrewbrody.service.ClimbSessionService

fun Route.climbSessionRoutes() {
    val climbSessionService : ClimbSessionService by inject()

    route("/climbSession") {
        get("/all") {
            val request = call.receive<BaseRq<Nothing>>()
            call.respond(climbSessionService.getAll(request.requestId))
        }
    }
}
