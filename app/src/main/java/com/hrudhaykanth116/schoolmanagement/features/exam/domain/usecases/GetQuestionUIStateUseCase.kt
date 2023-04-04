package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuestionUIStateUseCase @Inject constructor(
    private val parseMultipleChoiceData: ParseMultipleChoiceData,
) {

    suspend operator fun invoke(
        examData: GetExamDataResponse.Result,
        questionsScreenUIState: QuestionsScreenUIState
    ): QuestionsScreenUIState = withContext(Dispatchers.Default) {

        val questions: List<GetExamDataResponse.Result.QuestionDetail?> =
            examData.questionDetails!!
        val optionFormula =
            questions.firstOrNull()?.questionOptions?.firstOrNull()?.optionFormula.orEmpty()

        val questionDetail = questions.getOrNull(
            // json array starts from 0. So, subtract 1 to get correctly mapped question.
            questionsScreenUIState.questionNumber - 1
        )!!

        val question = if (questionDetail.questionDescription?.isNotBlank() == true) {
            questionDetail.questionDescription
        } else if(questionDetail.questionFormula?.isNotBlank() == true){
            // With the currently used lib to show math view, use \( and \) for formulae strings.
            "\\(${questionDetail.questionFormula}\\)"
        }else{
            // Not a possible case. Ignoring.
            "NA"
        }

        when (questionDetail.questionType) {
            "1" -> {
                val optionState = parseMultipleChoiceData(questionDetail)
                return@withContext questionsScreenUIState.copy(
                    question = question,
                    questionOptions = optionState,
                    questionTitle = UIText.StringRes(R.string.multiples_choices),
                    answerTitle = UIText.StringRes(R.string.choose_your_answer)
                )
            }
            else -> {
                return@withContext questionsScreenUIState.copy(
                    question = question,
                    questionOptions = null,
                    questionTitle = UIText.StringRes(R.string.question_),
                    answerTitle = UIText.StringRes(R.string.answer_)
                )
            }
        }
    }

}