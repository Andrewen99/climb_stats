package ru.andrewbrody.di

import ru.andrewbrody.service.TestService
import ru.andrewbrody.service.TestServiceImpl
import org.koin.dsl.module
import ru.andrewbrody.service.ClimbSessionService

val appModule = module {
    single<TestService>{ TestServiceImpl() }
    single { ClimbSessionService(get()) }

}