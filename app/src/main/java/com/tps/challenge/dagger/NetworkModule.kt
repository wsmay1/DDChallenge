package com.tps.challenge.dagger

import android.content.Context
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import com.tps.challenge.Constants
import com.tps.challenge.network.TPSService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides Network communication related instances.
 */
@Module
class NetworkModule {
    @Provides
    fun provideTPSService(): TPSService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient())
            .build()
        return retrofit.create(TPSService::class.java)
    }

    @Provides
    fun providePicasso(): Picasso {
        return Picasso.get()
    }
}
