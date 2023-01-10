package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.AloneListResDto
import com.android.hara.data.model.response.ResponseVoteDTO
import com.android.hara.domain.repository.HARARepository
import retrofit2.Response
import javax.inject.Inject

class HARARepositoryImpl @Inject constructor(
    private val HARAService: HARAService
) : HARARepository {
    override suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO {
        return HARAService.vote(request)
    }

    override suspend fun getAloneList(isSolved: Int): Response<AloneListResDto> {
        return HARAService.getAloneList(isSolved)
    }
}
