package com.hrudhaykanth116.schoolmanagement.features.exam.data.di

import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.ExamRemoteDataSource
import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.IExamRemoteDataSource
import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.retrofit.ExamApiService
import com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam.ExamRepository
import com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam.IExamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideExamRemoteDataSource(
        examApiService: ExamApiService,
    ): IExamRemoteDataSource = ExamRemoteDataSource(
        examApiService,
    )

    @Provides
    fun provideExamRepository(
        examRemoteDataSource: IExamRemoteDataSource
    ): IExamRepository = ExamRepository(
        examRemoteDataSource
    )

}