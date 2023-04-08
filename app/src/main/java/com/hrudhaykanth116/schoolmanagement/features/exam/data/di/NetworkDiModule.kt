package com.hrudhaykanth116.schoolmanagement.features.exam.data.di

import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.retrofit.ExamApiService
import com.hrudhaykanth116.schoolmanagement.secure.Urls
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkDiModule {

    @Provides
    fun provideBaseUrl() = Urls.BASE_URL

    @Provides
    @Singleton
    fun provideExamApiService(retrofit: Retrofit): ExamApiService =
        retrofit.create(ExamApiService::class.java)


}