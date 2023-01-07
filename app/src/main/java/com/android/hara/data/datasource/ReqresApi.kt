package com.android.hara.data.datasource

import com.android.hara.data.model.response.ReqresUserDTO
import com.android.hara.data.model.response.ResponseReqresListDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqresApi { //서버통신 전반적일 테스트를 위한 Reqres API
    @GET("api/users?")
    suspend fun getReqresList(@Query("page") page: Int = 2): ResponseReqresListDTO

    @POST("api/users")
    suspend fun postReqresUser(reqresUserDTO: ReqresUserDTO): ReqresUserDTO
}