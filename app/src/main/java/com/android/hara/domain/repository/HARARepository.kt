package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.data.model.response.ResponseVoteDTO
import retrofit2.Response

interface HARARepository {
    suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO

    suspend fun getAloneList(isSolved: Int): Response<WorryListResDto>
}
