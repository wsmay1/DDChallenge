package com.tps.challenge.features.storefeed

import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel

interface StoreFeedContract {

    interface Presenter {
        fun onViewLoad()
        fun attachView(providedView: StoreFeedContract.View)
    }

    interface View {
        fun onDataLoaded(data: List<StoreFeedAdapterItemModel>)
    }

    interface Repository {
        suspend fun loadInitialData()
        fun attachCallback(callback: Callback)

        interface Callback {
            fun onSuccess(result: List<StoreFeedAdapterItemModel>)
            fun onError()
        }
    }
}