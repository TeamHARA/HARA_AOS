package com.android.hara.data.datasource

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.ResponseVoteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// [서버통신 1] 요청/응답 dto data class를 만든다
// [서버통신 2] API 호출 함수를 만든다
interface HARAService {
    // 가장 먼저 어떠한 액션을 취할 건지 작성합니다.
    @POST("/worry")
    suspend fun vote(@Body request:RequestVoteDTO) : ResponseVoteDTO
    // suspend fun vote(@Body request:RequestVoteDTO) : Response<ResponseVoteDTO>
    // Response에서 별다른 걸 안해줘도 된다면 == 서버에서 status가 정확하게 온다면, Response 필요없음

    @GET("/worry/{categoryId}")
    suspend fun showAllPost(@Path("categoryId") categoryId: Int) : Response<AllPostResDto>
}