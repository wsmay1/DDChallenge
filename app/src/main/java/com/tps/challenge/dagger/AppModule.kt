package com.tps.challenge.dagger

import android.app.Application
import com.tps.challenge.TCApplication
import com.tps.challenge.features.storefeed.StoreFeedContract
import com.tps.challenge.features.storefeed.StoreFeedPresenterImpl
import com.tps.challenge.features.storefeed.StoreFeedRepository
import com.tps.challenge.network.TPSService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: TCApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun getStoreFeedPresenter(repository: StoreFeedContract.Repository): StoreFeedContract.Presenter {
        return StoreFeedPresenterImpl(repository)
    }

    @Provides
    @Singleton
    fun getStoreFeedRepository(service: TPSService): StoreFeedContract.Repository {
        return StoreFeedRepository(service)
    }

    // email Maria.sharkina@doordash.com
}
