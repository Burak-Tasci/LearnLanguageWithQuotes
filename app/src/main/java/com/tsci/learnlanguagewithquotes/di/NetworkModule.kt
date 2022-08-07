package com.tsci.learnlanguagewithquotes.di

import com.tsci.learnlanguagewithquotes.BuildConfig
import com.tsci.learnlanguagewithquotes.data.remote.api.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val KEY_HEADER_API_KEY = "X-RapidAPI-Key"
    private const val KEY_HEADER_HOST = "X-RapidAPI-Host"

    @Singleton
    @Provides
    fun provideQuoteApi(): QuoteApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(provideHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuoteApi::class.java)

    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideHttpClientInterceptor())
        .addNetworkInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(KEY_HEADER_API_KEY, BuildConfig.API_KEY)
                .addHeader(KEY_HEADER_HOST, BuildConfig.HOST)
                .build()
            return@addNetworkInterceptor chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideHttpClientInterceptor() = HttpLoggingInterceptor().setLevel(Level.BODY)


}