package com.android.hara.domain.repository

import com.android.hara.data.model.request.*
import com.android.hara.data.model.response.*

// [서버통신 3] 레트로핏 객체 호출 함수를 만든다
interface HARARepository {
    suspend fun getAllPost(categoryId: Int): AllPostResDto
    suspend fun getAloneList(isSolved: Int): WorryListResDto
    suspend fun getWithList(isSolved: Int): WorryListResDto
    suspend fun getRandom(): OnesecResDto
    suspend fun getLastWorry(): RandomListResDto
    suspend fun postVote(request: VoteReqDto): VoteResDto

    suspend fun patchDecideWith(decision: DecideWithReqDto): DecisionResDto

    suspend fun patchDecideAlone(decision: DecideAloneReqDto): DecisionResDto

    suspend fun postWorryAlone(request: WorryAloneRequestDto): WorryAloneResponseDto
    suspend fun postWorryWith(request: WorryWithRequestDto): WorryWithResponseDto

    suspend fun getDetailWith(worryId: Int): DetailWithResDto

    suspend fun getDetailAlone(worryId: Int): DetailAloneResDto
}