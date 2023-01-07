package com.android.hara.domain.repository

import com.android.hara.data.model.request.RequestReqresUserDTO
import com.android.hara.data.model.response.ReqresUserDTO
import com.android.hara.data.model.response.ResponseReqresListDTO


interface ReqresRepository {
    suspend fun postReqres(request: RequestReqresUserDTO): ReqresUserDTO

    suspend fun getReqres(): ResponseReqresListDTO
}