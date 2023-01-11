package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.request.WorryAloneRequestDto
import com.android.hara.data.model.request.WorryWithRequestDto
import com.android.hara.data.model.response.*
import retrofit2.Response

interface HARARepository {
    suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO

    suspend fun getAloneList(isSolved: Int): Response<WorryListResDto>

    suspend fun getWithList(isSolved: Int): Response<WorryListResDto>

    suspend fun getRandom(): Response<OnesecResDto>

    suspend fun getLastWorry(): Response<RandomListResDto>

    suspend fun postWorryAlone(request: WorryAloneRequestDto): Response<WorryAloneResponseDto>

    suspend fun postWorryWith(request: WorryWithRequestDto): Response<WorryWithResponseDto>
}
