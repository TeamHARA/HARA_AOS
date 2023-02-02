package com.android.hara.domain.repository

import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.request.WorryWithRequestDto
import com.android.hara.data.model.response.*

interface HaraWithRepository {

    suspend fun getAllPost(categoryId: Int): AllPostResDto

    suspend fun getWithList(isSolved: Int): WorryListResDto

    suspend fun postVote(request: VoteReqDto): VoteResDto

    suspend fun patchDecideWith(decision: DecideWithReqDto): DecisionResDto

    suspend fun postWorryWith(request: WorryWithRequestDto): WorryWithResponseDto

    suspend fun getDetailWith(worryId: Int): DetailWithResDto

}