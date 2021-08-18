package com.tps.challenge.features.storefeed

import android.util.Log
import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class StoreFeedPresenterImpl @Inject constructor(val repository: StoreFeedContract.Repository) : StoreFeedContract.Presenter, StoreFeedContract.Repository.Callback {

    private lateinit var storeFeedData: List<StoreFeedAdapterItemModel>
    private lateinit var view: StoreFeedContract.View

    override fun onViewLoad() {
        repository.attachCallback(this)
        GlobalScope.async {
            repository.loadInitialData()
        }
    }

    override fun onSuccess(result: List<StoreFeedAdapterItemModel>) {
        storeFeedData = result
        view.onDataLoaded(storeFeedData)
    }

    override fun onError() {
        Log.e("DataRetrival", "Failed")
    }

    override fun attachView(providedView: StoreFeedContract.View) {
        view = providedView
    }
}