package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.*
import com.android.hara.domain.repository.HARARepository
import retrofit2.Response
import javax.inject.Inject

class HARARepositoryImpl @Inject constructor(
    private val HARAService: HARAService
) : HARARepository {
    override suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO {
        return HARAService.vote(request)
    }

    override suspend fun getAloneList(isSolved: Int): Response<WorryListResDto> {
        return HARAService.getAloneList(isSolved)
    }

    override suspend fun getWithList(isSolved: Int): Response<WorryListResDto> {
        return HARAService.getWithList(isSolved)
    }

    override suspend fun getRandom(): Response<OnesecResDto> {
        return HARAService.getRandom()
    }

    override suspend fun getLastWorry(): Response<RandomListResDto> {
        return HARAService.getLastWorry()
    }

    override suspend fun patchDecideWith(decision: DecideWithReqDto): Response<DecisionResDto> {
        return HARAService.patchWithDecision(decision)
    }

    override suspend fun patchDecideAlone(decision: DecideAloneReqDto): Response<DecisionResDto> {
        return HARAService.patchAloneDecision(decision)
    }
}
