package com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.IExamRemoteDataSource
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExamRepository @Inject constructor(
    private val remoteDataSource: IExamRemoteDataSource
): IExamRepository {

    // TODO: Inject dispatcher
    private val dispatcher = Dispatchers.IO

    override suspend fun getExamData(): DataResult<GetExamDataResponse> = withContext(dispatcher){
        return@withContext remoteDataSource.getExamData()
    }


}