package com.android.hara.domain.repository

import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.*
import retrofit2.Response

interface HARARepository {
    suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO

    suspend fun getAloneList(isSolved: Int): Response<WorryListResDto>

    suspend fun getWithList(isSolved: Int): Response<WorryListResDto>

    suspend fun getRandom(): Response<OnesecResDto>

    suspend fun getLastWorry(): Response<RandomListResDto>

    suspend fun patchDecideWith(decision: DecideWithReqDto): Response<DecisionResDto>

    suspend fun patchDecideAlone(decision: DecideAloneReqDto): Response<DecisionResDto>
}
