package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.VoteResDto
import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.OnesecResDto
import com.android.hara.data.model.response.RandomListResDto
import com.android.hara.data.model.response.ResponseVoteDTO
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.domain.repository.HARARepository
import retrofit2.Response
import javax.inject.Inject

// [서버통신 4] HARARepository에서 선언한 interface를 실제로 구현한다
// 서비스(=레트로핏 객체)를 불러온다 (@inject로 주입한다)
// [서버통신 5] (@HiltViewModel 사용)
// view model에 서버통신 성공/실패 시 작업을 정의한다 (HomeViewModel.kt 참고)

class HARARepositoryImpl @Inject constructor(
    private val HARAService: HARAService
) : HARARepository {

    override suspend fun getAllPost(categoryId: Int): Response<AllPostResDto> {
        return HARAService.showAllPost(categoryId)
    }

    override suspend fun postVote(request: VoteReqDto): Response<VoteResDto> {
        return HARAService.vote(request)
    }

    override suspend fun getAloneList(isSolved: Int): Response<WorryListResDto> {
        return HARAService.getAloneList(isSolved)
    }

    override suspend fun getWithList(isSolved: Int): Response<WorryListResDto> {
        return HARAService.getWithList(isSolved)
    }

    override suspend fun getRandom(): Response<OnesecResDto> {
        return HARAService.getRandom()
    }

    override suspend fun getLastWorry(): Response<RandomListResDto> {
        return HARAService.getLastWorry()
    }
}
