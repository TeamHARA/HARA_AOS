package com.android.hara.domain.repository

import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.WorryAloneRequestDto
import com.android.hara.data.model.response.*

// [서버통신 3] 레트로핏 객체 호출 함수를 만든다
interface HaraAloneRepository {

    suspend fun getAloneList(isSolved: Int): WorryListResDto

    suspend fun getRandom(): OnesecResDto

    suspend fun getLastWorry(): RandomListResDto

    suspend fun patchDecideAlone(decision: DecideAloneReqDto): DecisionResDto

    suspend fun postWorryAlone(request: WorryAloneRequestDto): WorryAloneResponseDto

    suspend fun getDetailAlone(worryId: Int): DetailAloneResDto

}