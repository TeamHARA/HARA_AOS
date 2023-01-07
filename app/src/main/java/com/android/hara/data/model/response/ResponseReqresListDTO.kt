package com.android.hara.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ResponseReqresListDTO(
    @SerialName("page")
    val page: Int?, // 2
    @SerialName("per_page")
    val perPage: Int?, // 6
    @SerialName("total")
    val total: Int?, // 12
    @SerialName("total_pages")
    val totalPages: Int?, // 2
    @SerialName("data")
    val `data`: List<Data?>?,
    @SerialName("support")
    val support: Support?
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int?, // 7
        @SerialName("email")
        val email: String?, // michael.lawson@reqres.in
        @SerialName("first_name")
        val firstName: String?, // Michael
        @SerialName("last_name")
        val lastName: String?, // Lawson
        @SerialName("avatar")
        val avatar: String? // https://reqres.in/img/faces/7-image.jpg
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String?, // https://reqres.in/#support-heading
        @SerialName("text")
        val text: String? // To keep ReqRes free, contributions towards server costs are appreciated!
    )
}