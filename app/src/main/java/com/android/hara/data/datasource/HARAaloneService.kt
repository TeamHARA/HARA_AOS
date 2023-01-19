package com.android.hara.data.datasource

import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.WorryAloneRequestDto
import com.android.hara.data.model.response.*
import retrofit2.http.*


interface HARAaloneService {
    @GET("/worry/alone/list/{isSolved}")
    suspend fun getAloneList(@Path("isSolved") isSolved: Int): WorryListResDto

    @GET("/random")
    suspend fun getRandom(): OnesecResDto

    @GET("/random/list")
    suspend fun getLastWorry(): RandomListResDto

    @PATCH("/worry/alone")
    suspend fun patchAloneDecision(@Body decision: DecideAloneReqDto): DecisionResDto

    @POST("/worry/alone")
    suspend fun postWorryAlone(@Body request: WorryAloneRequestDto): WorryAloneResponseDto

    @GET("/worry/alone/{worryId}")
    suspend fun getDetailAlone(@Path("worryId") worryId: Int): DetailAloneResDto
}
