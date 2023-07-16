package ru.andrewbrody.service

//import org.koin.java.KoinJavaComponent.get
import dto.EntityStatus
import dto.ClimbRouteDto
import error.ClimbStatsError
import model.BaseRq
import model.BaseRs
import model.ClimbRoute
import repo.RouteRepo
import repo.collapse

class RouteService(
    private val routeRepo: RouteRepo
) {

    suspend fun getAll() : BaseRs<List<ClimbRoute>> {
        return routeRepo.getAll().toBaseRs()
    }

    suspend fun save(rq: BaseRq<ClimbRouteDto>) : BaseRs<ClimbRoute> {
        val climbRoute = rq.data.toRoute()
        when (climbRoute.entityStatus) {
            EntityStatus.NEW -> return routeRepo.save(climbRoute).toBaseRs(rq.requestId)
            EntityStatus.UPDATED -> return routeRepo.update(climbRoute).toBaseRs(rq.requestId)
            else -> {
                return BaseRs(
                    requestId = rq.requestId,
                    data = null,
                    success = false,
                    errors = listOf(
                        ClimbStatsError(
                            code = "incorrect entity status",
                            group = "validation",
                            field = "entityStatus",
                            message = "Entity status should be NEW or UPDATED for saving",
                            error = null,
                            )
                    )

                )
            }
        }
    }

    suspend fun delete(rq: BaseRq<ClimbRouteDto>) : BaseRs<ClimbRoute> {
        val climbRoute = rq.data.toRoute()
        return routeRepo.update(climbRoute).toBaseRs(rq.requestId)
    }

}