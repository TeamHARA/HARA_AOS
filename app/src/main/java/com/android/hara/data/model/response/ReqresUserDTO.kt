package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReqresUserDTO(
    @SerialName("name")
    val name: String, // morpheus
    @SerialName("job")
    val job: String, // leader
    @SerialName("id")
    val id: String, // 128
    @SerialName("createdAt")
    val createdAt: String // 2023-01-07T17:36:19.822Z
)