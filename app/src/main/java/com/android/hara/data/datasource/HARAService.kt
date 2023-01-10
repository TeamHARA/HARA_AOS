package com.android.hara.data.datasource

import com.android.hara.data.model.request.RequestVoteDTO
import com.android.hara.data.model.response.ResponseVoteDTO
import com.android.hara.data.model.response.WorryListResDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HARAService {
    // 어떠한 액션을 취할건지 가장 먼저 작성해야합니다.
    @POST("/worry")
    suspend fun vote(@Body request: RequestVoteDTO): ResponseVoteDTO
    // suspend fun vote(@Body request:RequestVoteDTO) : Response<ResponseVoteDTO>
    // Response에서 별다른 걸 안해줘도 된다면 == 서버에서 status가 정확하게 온다며 Response 필요없음

    @GET("/worry/alone/list/{isSolved}")
    suspend fun getAloneList(@Path("isSolved") isSolved: Int): Response<WorryListResDto>
}
