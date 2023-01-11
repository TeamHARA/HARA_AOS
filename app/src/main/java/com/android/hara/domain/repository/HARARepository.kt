package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.OnesecResDto
import com.android.hara.data.model.response.RandomListResDto
import com.android.hara.data.model.response.ResponseVoteDTO
import com.android.hara.data.model.response.WorryListResDto
import retrofit2.Response

interface HARARepository {
    suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO

    suspend fun getAloneList(isSolved: Int): Response<WorryListResDto>

    suspend fun getWithList(isSolved: Int): Response<WorryListResDto>

    suspend fun getRandom(): Response<OnesecResDto>

    suspend fun getLastWorry(): Response<RandomListResDto>
}
