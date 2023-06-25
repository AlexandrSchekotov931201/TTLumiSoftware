package com.apshheko.ttlumisoftware.di

import com.apshheko.ttlumisoftware.network.api.EtherscanApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .callTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideEtherscanApi(retrofit: Retrofit): EtherscanApi = retrofit.create(EtherscanApi::class.java)

    private companion object {
        const val TIME_OUT = 50000L
        const val BASE_URL = "https://api.etherscan.io"
    }
}
