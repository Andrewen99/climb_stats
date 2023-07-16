package ru.andrewbrody.di

import io.ktor.server.application.*
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import ru.andrewbrody.service.TestService
import ru.andrewbrody.service.TestServiceImpl
import org.koin.dsl.module
import repo.*
import ru.andrewbrody.*
import ru.andrewbrody.config.ApplicationConfig
import ru.andrewbrody.config.RepoType
import ru.andrewbrody.config.createApplicationConfig
import ru.andrewbrody.service.ClimbSessionService
import ru.andrewbrody.service.RouteService
import java.util.*

fun appModule(application: Application) = module {
    val config = createApplicationConfig(application.environment)
    single { config }
    when (config.repoType) {
        RepoType.NONE -> throw RuntimeException("No repo type defined in application properties file")
        RepoType.IN_MEMORY -> {
            single<ClimbRepo> { ClimbRepoInMemory() }
            single<ClimbSessionsRepo> { ClimbSessionRepoInMemory() }
            single<ExerciseRepo> { ExerciseRepoInMemory() }
            single<RouteRepo> { RouteRepoInMemory() }
            single<StrengthTrainingRepo> { StrengthTrainingRepoInMemory() }
        }
        RepoType.MONGODB -> {}
    }
    single { RouteService(get()) } withOptions {
        createdAtStart()
    }
    single<TestService>{ TestServiceImpl() }
    single { ClimbSessionService(get()) }
    single {RouteService(get())}

}