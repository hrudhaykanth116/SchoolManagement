package com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse

interface IExamRemoteDataSource {

    suspend fun getExamData(): DataResult<GetExamDataResponse>

}