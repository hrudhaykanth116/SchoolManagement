package com.hrudhaykanth116.schoolmanagement.features.exam.data.datasources.remote.retrofit

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.secure.Urls
import retrofit2.Response
import retrofit2.http.GET

interface ExamApiService {

    // TODO: Break down url to base url and params
    @GET(Urls.GET_EXAM_DATA_URL)
    suspend fun getExamData(): Response<GetExamDataResponse>

}
