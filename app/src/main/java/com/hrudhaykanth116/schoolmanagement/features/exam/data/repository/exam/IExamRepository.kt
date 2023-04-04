package com.hrudhaykanth116.schoolmanagement.features.exam.data.repository.exam

import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse

interface IExamRepository {

    suspend fun getExamData(): DataResult<GetExamDataResponse>

}