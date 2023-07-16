package ru.andrewbrody.routes

import dto.ClimbRouteDto
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.BaseRq
import org.koin.ktor.ext.inject
import ru.andrewbrody.service.RouteService

fun Route.climbRoutesRoute() {
    val routeService : RouteService by inject()

    route("/climb-routes") {
        get("/all") {
            val response = routeService.getAll()
            call.respond(response.copyWithData(response.data?.map { it.toClimbRouteDto() }))
        }
        post("/save") {
            val rq = call.receive<BaseRq<ClimbRouteDto>>()
            val rs = routeService.save(rq)
            call.respond(rs.copyWithData(rs.data?.toClimbRouteDto()))
        }

        post("/delete") {
            val rq = call.receive<BaseRq<ClimbRouteDto>>()
            val rs = routeService.delete(rq)
            call.respond(rs.copyWithData(rs.data?.toClimbRouteDto()))
        }
    }
}