package com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network

import com.squareup.moshi.Json

data class GetExamDataResponse(
    @Json(name = "ErrorMessage")
    val errorMessage: String? = null,
    @Json(name = "Result")
    val result: Result? = null,
    @Json(name = "StatusCode")
    val statusCode: Int? = null,
    @Json(name = "Version")
    val version: String? = null
) {
    data class Result(
        @Json(name = "PaperDetails")
        val paperDetails: List<PaperDetail?>? = null,
        @Json(name = "QuestionDetails")
        val questionDetails: List<QuestionDetail?>? = null,
        @Json(name = "ServerTime")
        val serverTime: List<ServerTime?>? = null,
        @Json(name = "Solution")
        val solution: Any? = null,
        @Json(name = "Status")
        val status: List<Statu?>? = null
    ) {
        data class PaperDetail(
            @Json(name = "AcyId")
            val acyId: Int? = null,
            @Json(name = "DurationMins")
            val durationMins: Int? = null,
            @Json(name = "ExamPaperId")
            val examPaperId: Int? = null,
            @Json(name = "ExamPaperName")
            val examPaperName: String? = null,
            @Json(name = "MarksPerQuestion")
            val marksPerQuestion: Int? = null,
            @Json(name = "QuestionCount")
            val questionCount: Int? = null,
            @Json(name = "StartDate")
            val startDate: String? = null,
            @Json(name = "StartTime")
            val startTime: String? = null,
            @Json(name = "SubjectId")
            val subjectId: Int? = null,
            @Json(name = "SubjectIndexId")
            val subjectIndexId: Int? = null,
            @Json(name = "SubscriptionId")
            val subscriptionId: Int? = null,
            @Json(name = "UserID")
            val userID: Int? = null
        )

        data class QuestionDetail(
            @Json(name = "ActualOption")
            val actualOption: Int? = null,
            @Json(name = "DifficultyLevelId")
            val difficultyLevelId: Int? = null,
            @Json(name = "Ischoice")
            val ischoice: Int? = null,
            @Json(name = "QuestionDescription")
            val questionDescription: String? = null,
            @Json(name = "QuestionFormula")
            val questionFormula: String? = null,
            @Json(name = "QuestionId")
            val questionId: Int? = null,
            @Json(name = "QuestionImage")
            val questionImage: String? = null,
            @Json(name = "QuestionOptions")
            val questionOptions: List<QuestionOption?>? = null,
            @Json(name = "QuestionType")
            val questionType: String? = null,
            @Json(name = "SlNo")
            val slNo: Int? = null,
            @Json(name = "Solution")
            val solution: List<Any?>? = null,
            @Json(name = "SubjectID")
            val subjectID: Int? = null,
            @Json(name = "SubjectName")
            val subjectName: String? = null
        ) {
            data class QuestionOption(
                @Json(name = "CorrectOption")
                val correctOption: Int? = null,
                @Json(name = "Description")
                val description: String? = null,
                @Json(name = "Option1")
                val option1: Int? = null,
                @Json(name = "Option2")
                val option2: Int? = null,
                @Json(name = "OptionFormula")
                val optionFormula: String? = null,
                @Json(name = "OptionId")
                val optionId: Int? = null,
                @Json(name = "OptionImage")
                val optionImage: String? = null,
                @Json(name = "QuestionId")
                val questionId: Int? = null,
                @Json(name = "UserPracPaperId")
                val userPracPaperId: Int? = null
            )
        }

        data class ServerTime(
            @Json(name = "CurrentDate")
            val currentDate: String? = null,
            @Json(name = "CurrentTime")
            val currentTime: String? = null
        )

        data class Statu(
            @Json(name = "StatusCode")
            val statusCode: Int? = null,
            @Json(name = "StatusId")
            val statusId: Int? = null,
            @Json(name = "StatusMessage")
            val statusMessage: String? = null
        )
    }
}