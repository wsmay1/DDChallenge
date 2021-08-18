package com.tps.challenge.network.model

import com.google.gson.annotations.SerializedName

/**
 * Store remote data model.
 */
data class StoreResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("cover_img_url")
    val coverImgUrl: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("delivery_fee")
    val deliveryFeeCents: String
)
