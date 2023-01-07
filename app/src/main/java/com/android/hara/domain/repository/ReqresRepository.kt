package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestReqresUserDTO
import com.android.hara.data.model.response.ReqresUserDTO
import com.android.hara.data.model.response.ResponseReqresListDTO
import retrofit2.Response


interface ReqresRepository {
    suspend fun postReqres(request: RequestReqresUserDTO): Response<ReqresUserDTO>

    suspend fun getReqres(): Response<ResponseReqresListDTO>
}