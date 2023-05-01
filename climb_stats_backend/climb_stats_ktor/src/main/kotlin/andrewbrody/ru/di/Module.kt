package andrewbrody.ru.di

import andrewbrody.ru.service.TestService
import andrewbrody.ru.service.TestServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<TestService>{ TestServiceImpl() }
}