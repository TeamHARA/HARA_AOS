package com.android.hara.data.datasource

import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.data.model.request.VoteReqDto
import com.android.hara.data.model.request.WorryWithRequestDto
import com.android.hara.data.model.response.*
import retrofit2.http.*

// [서버통신 1] 요청/응답 dto data class를 만든다
// [서버통신 2] API 호출 함수를 만든다
interface HaraWithService {
    // 가장 먼저 어떠한 액션을 취할 건지 작성합니다.
    /*
    @POST("/worry")
    suspend fun vote(@Body request:VoteReqDto) : VoteResDto
    */
    // suspend fun vote(@Body request:VoteReqDto) : Response<VoteResDto>
    // Response에서 별다른 걸 안해줘도 된다면 == 서버에서 status가 정확하게 온다면, Response 필요없음

    /*
    함께해라 탭 전체 고민
     */
    @GET("/worry/{categoryId}")
    suspend fun showAllPost(@Path("categoryId") categoryId: Int): AllPostResDto

    @POST("/worry")
    suspend fun postVote(@Body request: VoteReqDto): VoteResDto

    @GET("/worry/with/list/{isSolved}")
    suspend fun getWithList(@Path("isSolved") isSolved: Int): WorryListResDto

    /*
    최종결정
     */
    @PATCH("/worry/with")
    suspend fun patchWithDecision(@Body decision: DecideWithReqDto): DecisionResDto

    @POST("/worry/with")
    suspend fun postWorryWith(@Body request: WorryWithRequestDto): WorryWithResponseDto

    ///worry/with/:worryId
    @GET("/worry/with/{worryId}")
    suspend fun getDetailWith(@Path("worryId") worryId: Int): DetailWithResDto

}