package com.android.hara.data.datasource

import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.VoteResDto
import retrofit2.Response
import retrofit2.http.*

// [서버통신 1] 요청/응답 dto data class를 만든다
// [서버통신 2] API 호출 함수를 만든다
interface HARAService {
    // 가장 먼저 어떠한 액션을 취할 건지 작성합니다.
    /*
    @POST("/worry")
    suspend fun vote(@Body request:VoteReqDto) : VoteResDto
    */
    // suspend fun vote(@Body request:VoteReqDto) : Response<VoteResDto>
    // Response에서 별다른 걸 안해줘도 된다면 == 서버에서 status가 정확하게 온다면, Response 필요없음

    @GET("/worry/{categoryId}")
    suspend fun showAllPost(@Path("categoryId") categoryId: Int) : Response<AllPostResDto>

    // TODO: Headers를 제거해도 post 요청이 제대로 가는지 확인
    @Headers("Content-Type: application/json")
    @POST("/worry")
    suspend fun vote(@Body request: VoteReqDto) : Response<VoteResDto>
}