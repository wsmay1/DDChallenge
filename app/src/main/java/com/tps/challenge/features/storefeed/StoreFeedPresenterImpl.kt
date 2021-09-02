package com.tps.challenge.features.storefeed

import android.util.Log
import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreFeedPresenterImpl @Inject constructor(val repository: StoreFeedContract.Repository) : StoreFeedContract.Presenter, StoreFeedContract.Repository.Callback {

    private lateinit var storeFeedData: List<StoreFeedAdapterItemModel>
    private lateinit var view: StoreFeedContract.View
    var loadingJob: Job? = null

    override fun onViewLoad() {
        loadingJob = CoroutineScope(Dispatchers.IO).launch {
            repository.loadInitialData(this@StoreFeedPresenterImpl)
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

    override fun onViewDestroyed() {
        when (loadingJob?.isActive) {
            true -> loadingJob?.cancel(null)
        }
    }
}