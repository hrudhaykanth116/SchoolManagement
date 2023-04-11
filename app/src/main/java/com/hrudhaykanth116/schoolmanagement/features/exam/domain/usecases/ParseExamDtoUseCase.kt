package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.*
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.*
import javax.inject.Inject

class ParseExamDtoUseCase @Inject constructor(
    private val getQuestionUIStateUseCase: GetQuestionUIStateUseCase
) {

    suspend operator fun invoke(examData: GetExamDataResponse.Result): ExamUIState {


        val questionsUIState = mutableListOf<QuestionUIState>()
        examData.questionDetails!!.forEachIndexed { index, questionDetail: GetExamDataResponse.Result.QuestionDetail? ->
            val questionUIState: QuestionUIState = getQuestionUIStateUseCase(questionDetail).copy(
                // 0th question in list will be the first question on UI
                questionNumber = index + 1
            )
            // No index or sorting data. So, assuming the order from backend.
            questionsUIState.add(questionUIState)

            // TODO: This may be efficient given that we are already looping here

            /*val existingFilterOption = filterIconsList.find {
                it.name == questionUIState.subject
                // || it.name == questionUIState.answerType.filterName
            }

            val filterOption = existingFilterOption?.copy(
                count = existingFilterOption.count + 1
            ) ?: kotlin.run {
                filterIconsList.add(
                    FilterOption(questionUIState.subject!!, 0)
                )
            }
            */

        }

        // TODO: We may use already extracted grouped list here. Need to handle Showing next question
        val subjectWiseGroup: Map<String, List<QuestionUIState>> = questionsUIState.groupBy {
            it.subject!!
        }

        val answerTypeWiseGroup: Map<String, List<String>> = questionsUIState.groupBy(
            {
                it.answerUIState.filterName
            }
        ) {
            // TODO: Revisit logic. Also Use constants.

            when (it.answerUIState) {
                is FillInTheBlankAnswerUIState -> "Fill in the blanks"
                is MultipleAnswersUIState -> "Multiple answers"
                is MultipleChoicesAnswerUIState -> "Multiple choices"
                is ShortAnswerUIState -> "Short answer"
                is TrueFalseAnswerUIState -> "True False"
                is UnknownAnswerUIState -> "Unknown"
                is EasyAnswerUIState -> "Easy"
            }
        }

        val filterOptionsListStates: List<FilterOptionState> = (subjectWiseGroup + answerTypeWiseGroup).map {
            FilterOptionState(
                it.key, it.value.count()
            )
        }

        val filterOptionsState = FilterOptionsState(
            filterOptionsListStates
        )


        return ExamUIState(
            questionsList = questionsUIState,
            // Initially filtered list would be same as the questions list. Or change this behavior in viewmodel
            questionsToDisplayList = questionsUIState,
            filterOptionsState = filterOptionsState
        )


    }

}