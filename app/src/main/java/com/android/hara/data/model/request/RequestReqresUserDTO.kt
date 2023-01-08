package com.android.hara.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestReqresUserDTO(
    //TODO 삭제예정
    @SerialName("name")
    val name: String, // morpheus
    @SerialName("job")
    val job: String // leader
)