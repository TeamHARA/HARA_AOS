package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.ResponseVoteDTO
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
    override suspend fun postVote(request: RequestVoteDTO): ResponseVoteDTO {
        return HARAService.vote(request)
    }

    override suspend fun getAllPost(categoryId: Int): Response<AllPostResDto> {
        return HARAService.showAllPost(categoryId)
    }
}