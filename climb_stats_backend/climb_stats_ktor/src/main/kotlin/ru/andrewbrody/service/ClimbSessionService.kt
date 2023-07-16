package ru.andrewbrody.service

import dto.ClimbSessionDto
import dto.EntityStatus
import model.BaseRs
import model.ClimbSession
import repo.ClimbSessionsRepo
import stubs.UserStubs

class ClimbSessionService(
    private val repo: ClimbSessionsRepo
) {

    suspend fun getAll(requestId: String) : BaseRs<List<ClimbSession>> {
        val userId = UserStubs.USER1.id!!
        val dbResponse = repo.getAll(userId)
        return dbResponse.toBaseRs(requestId)
    }



}