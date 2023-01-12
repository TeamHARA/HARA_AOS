package com.android.hara.domain.repository

import com.android.hara.data.model.request.*
import com.android.hara.data.model.response.*
import retrofit2.Response

// [서버통신 3] 레트로핏 객체 호출 함수를 만든다
interface HARARepository {
    suspend fun getAloneList(isSolved: Int): Response<WorryListResDto>
    suspend fun getWithList(isSolved: Int): Response<WorryListResDto>
    suspend fun getRandom(): Response<OnesecResDto>
    suspend fun getLastWorry(): Response<RandomListResDto>
    suspend fun getAllPost(categoryId: Int): Response<AllPostResDto>
    suspend fun postVote(request: VoteReqDto): Response<VoteResDto>

    suspend fun patchDecideWith(decision: DecideWithReqDto): Response<DecisionResDto>

    suspend fun patchDecideAlone(decision: DecideAloneReqDto): Response<DecisionResDto>

    suspend fun postWorryAlone(request: WorryAloneRequestDto): Response<WorryAloneResponseDto>

    suspend fun postWorryWith(request: WorryWithRequestDto): Response<WorryWithResponseDto>

    suspend fun getDetailWith(worryId: Int): Response<DetailWithResDto>

    suspend fun getDetailAlone(worryId: Int): Response<DetailAloneResDto>
}