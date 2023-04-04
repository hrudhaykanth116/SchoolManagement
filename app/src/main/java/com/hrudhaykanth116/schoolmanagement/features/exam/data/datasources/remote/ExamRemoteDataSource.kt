package com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.common.data.datasources.remote.NetworkDataSource
import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.retrofit.ExamApiService
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ExamRemoteDataSource @Inject constructor(
    private val examApiService: ExamApiService
): NetworkDataSource(), IExamRemoteDataSource{

    // getResult in NetworkDataSource is responsible for thread and data management

    // TODO: Enforce usage of getResult which used IO dispatcher.

    override suspend fun getExamData(): DataResult<GetExamDataResponse> {
        return getResult {
            examApiService.getExamData()
        }
    }


}