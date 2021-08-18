package com.tps.challenge.features.storefeed

import com.tps.challenge.Constants
import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel
import com.tps.challenge.network.TPSService
import com.tps.challenge.network.model.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StoreFeedRepository @Inject constructor(private val storeFeedService: TPSService) : StoreFeedContract.Repository, Callback<List<StoreResponse>> {

    private lateinit var stores: List<StoreFeedAdapterItemModel>
    private lateinit var callback: StoreFeedContract.Repository.Callback

    override suspend fun loadInitialData() {
        coroutineScope {
            launch(Dispatchers.IO) {
                storeFeedService.getStoreFeed(Constants.DEFAULT_LATITUDE, Constants.DEFAULT_LONGITUDE).enqueue(this@StoreFeedRepository)
            }
        }
    }

    override fun attachCallback(callback: StoreFeedContract.Repository.Callback) {
        this.callback = callback
    }

    private fun mapResponseItemToAdapterItem(responseList: List<StoreResponse>): List<StoreFeedAdapterItemModel> {
        val processedList = mutableListOf<StoreFeedAdapterItemModel>()
        responseList.forEach {
            processedList.add(StoreFeedAdapterItemModel(it.id, it.coverImgUrl, it.name, it.description))
        }
        return processedList
    }

    override fun onResponse(
        call: Call<List<StoreResponse>>,
        response: Response<List<StoreResponse>>
    ) {
        stores = mapResponseItemToAdapterItem(response.body() ?: listOf())
        callback.onSuccess(stores)
    }

    override fun onFailure(call: Call<List<StoreResponse>>, t: Throwable) {
        callback.onError()
    }
}