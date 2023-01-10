package com.android.hara.domain.repository

import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.VoteResDto
import retrofit2.Response

// [서버통신 3] 레트로핏 객체 호출 함수를 만든다
interface HARARepository {
    suspend fun getAllPost(categoryId: Int): Response<AllPostResDto>
    suspend fun postVote(request: VoteReqDto): Response<VoteResDto>
}