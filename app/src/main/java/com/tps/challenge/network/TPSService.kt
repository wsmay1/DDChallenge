package com.tps.challenge.network

import com.tps.challenge.network.model.StoreDetailsResponse
import com.tps.challenge.network.model.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Communicates with the TPS Challenge backend to obtain data.
 */
interface TPSService {
    /**
     * Returns the Store feed per location provided.
     */
    @GET("v1/feed")
    fun getStoreFeed(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double
    ): Call<List<StoreResponse>>

    /**
     * Returns a detailed specification for the Store.
     */
    @GET("v1/stores/{id}")
    fun getStoreDetails(
        @Path("id") storeId: String
    ): Call<StoreDetailsResponse>
}
