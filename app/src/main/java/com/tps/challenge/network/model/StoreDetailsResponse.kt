package com.tps.challenge.network.model

import com.google.gson.annotations.SerializedName

/**
 * Store details remote data model.
 */
data class StoreDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("cover_img_url")
    val coverImgUrl: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("status")
    val deliveryEta: String,
    @SerializedName("status_type")
    val status: String,
    @SerializedName("delivery_fee")
    val deliveryFeeCents: Int,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("address")
    val address: StoreAddressResponse
)
