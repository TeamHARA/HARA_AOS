package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAaloneService
import com.android.hara.data.model.request.*
import com.android.hara.data.model.response.*
import com.android.hara.domain.repository.HARARepository
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

// [서버통신 4] HARARepository에서 선언한 interface를 실제로 구현한다
// 서비스(=레트로핏 객체)를 불러온다 (@inject로 주입한다)
// [서버통신 5] (@HiltViewModel 사용)
// view model에 서버통신 성공/실패 시 작업을 정의한다 (HomeViewModel.kt 참고)

class HARARepositoryImpl @Inject constructor(
    private val HARAaloneService: HARAaloneService
) : HARARepository {

    override suspend fun getAllPost(categoryId: Int): AllPostResDto {
        return HARAaloneService.showAllPost(categoryId)
    }

    override suspend fun postVote(request: VoteReqDto): Response<VoteResDto> {
        return HARAaloneService.vote(request)
    }

    override suspend fun getAloneList(isSolved: Int): Response<WorryListResDto> {
        return HARAaloneService.getAloneList(isSolved)
    }

    override suspend fun getWithList(isSolved: Int): Response<WorryListResDto> {
        return HARAaloneService.getWithList(isSolved)
    }

    override suspend fun getRandom(): Response<OnesecResDto> {
        return HARAaloneService.getRandom()
    }

    override suspend fun getLastWorry(): Response<RandomListResDto> {
        return HARAaloneService.getLastWorry()
    }

    override suspend fun patchDecideWith(decision: DecideWithReqDto): Response<DecisionResDto> {
        return HARAaloneService.patchWithDecision(decision)
    }

    override suspend fun patchDecideAlone(decision: DecideAloneReqDto): Response<DecisionResDto> {
        return HARAaloneService.patchAloneDecision(decision)
    }

    override suspend fun postWorryWith(request: WorryWithRequestDto): Response<WorryWithResponseDto> {
        Timber.e(request.toString())
        return HARAaloneService.postWorryWith(request)
    }

    override suspend fun postWorryAlone(request: WorryAloneRequestDto): Response<WorryAloneResponseDto> {
        return HARAaloneService.postWorryAlone(request)
    }

    override suspend fun getDetailWith(worryId: Int): Response<DetailWithResDto> {
        return HARAaloneService.getDetailWith(worryId)
    }

    override suspend fun getDetailAlone(worryId: Int): Response<DetailAloneResDto> {
        return HARAaloneService.getDetailAlone(worryId)
    }
}
