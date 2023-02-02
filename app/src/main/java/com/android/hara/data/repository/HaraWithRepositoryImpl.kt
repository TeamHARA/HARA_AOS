package com.android.hara.data.repository

import com.android.hara.data.datasource.HaraWithService
import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.request.WorryWithRequestDto
import com.android.hara.data.model.response.*
import com.android.hara.domain.repository.HaraWithRepository
import timber.log.Timber
import javax.inject.Inject

class HaraWithRepositoryImpl @Inject constructor(
    private val HaraWithService: HaraWithService
) : HaraWithRepository {

    override suspend fun postVote(request: VoteReqDto): VoteResDto {
        return HaraWithService.postVote(request)
    }

    override suspend fun getAllPost(categoryId: Int): AllPostResDto {
        return HaraWithService.showAllPost(categoryId)
    }

    override suspend fun getWithList(isSolved: Int): WorryListResDto {
        return HaraWithService.getWithList(isSolved)
    }

    override suspend fun patchDecideWith(decision: DecideWithReqDto): DecisionResDto {
        return HaraWithService.patchWithDecision(decision)
    }

    override suspend fun getDetailWith(worryId: Int): DetailWithResDto {
        return HaraWithService.getDetailWith(worryId)
    }

    override suspend fun postWorryWith(request: WorryWithRequestDto): WorryWithResponseDto {
        Timber.e(request.toString())
        return HaraWithService.postWorryWith(request)
    }

}