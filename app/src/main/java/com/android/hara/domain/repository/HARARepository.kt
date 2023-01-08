package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.ResponseVoteDTO

interface HARARepository {
    suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO

}