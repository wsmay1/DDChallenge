package com.tps.challenge.network.model

import com.google.gson.annotations.SerializedName

/**
 * Store address remote data.
 */
data class StoreAddressResponse(
    @SerializedName("printable_address")
    val printableAddress: String
)
