package com.android.hara.data.repository

import com.android.hara.data.datasource.HaraAloneService
import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.WorryAloneRequestDto
import com.android.hara.data.model.response.*
import com.android.hara.domain.repository.HaraAloneRepository
import javax.inject.Inject

// [서버통신 4] HARARepository에서 선언한 interface를 실제로 구현한다
// 서비스(=레트로핏 객체)를 불러온다 (@inject로 주입한다)
// [서버통신 5] (@HiltViewModel 사용)
// view model에 서버통신 성공/실패 시 작업을 정의한다 (HomeViewModel.kt 참고)

class HaraAloneRepositoryImpl @Inject constructor(
    private val HaraAloneService: HaraAloneService
) : HaraAloneRepository {

    override suspend fun getAloneList(isSolved: Int): WorryListResDto {
        return HaraAloneService.getAloneList(isSolved)
    }

    override suspend fun getRandom(): OnesecResDto {
        return HaraAloneService.getRandom()
    }

    override suspend fun getLastWorry(): RandomListResDto {
        return HaraAloneService.getLastWorry()
    }

    override suspend fun patchDecideAlone(decision: DecideAloneReqDto): DecisionResDto {
        return HaraAloneService.patchAloneDecision(decision)
    }

    override suspend fun postWorryAlone(request: WorryAloneRequestDto): WorryAloneResponseDto {
        return HaraAloneService.postWorryAlone(request)
    }

    override suspend fun getDetailAlone(worryId: Int): DetailAloneResDto {
        return HaraAloneService.getDetailAlone(worryId)
    }

}
