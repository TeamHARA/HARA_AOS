package com.android.hara.data.repository

import com.android.hara.data.datasource.HARAService
import com.android.hara.data.datasource.ReqresApi
import com.android.hara.data.model.request.RequestReqresUserDTO
import com.android.hara.data.model.response.ReqresUserDTO
import com.android.hara.data.model.response.ResponseReqresListDTO
import com.android.hara.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val ReqresApi: ReqresApi
) :ReqresRepository{
    override suspend fun postReqres(request: RequestReqresUserDTO): ReqresUserDTO {
        return ReqresApi.postReqresUser(request)
    }

    override suspend fun getReqres(): ResponseReqresListDTO {
        return ReqresApi.getReqresList()
    }
}