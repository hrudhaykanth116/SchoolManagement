package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerType
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuestionUIStateUseCase @Inject constructor(
    private val parseMultipleChoiceData: ParseMultipleChoiceData,
) {

    suspend operator fun invoke(
        questionDetail: GetExamDataResponse.Result.QuestionDetail?
    ): QuestionUIState = withContext(Dispatchers.Default) {

        questionDetail ?: return@withContext QuestionUIState(
            errorMessage = UIText.Text("No data")
        )

        val question = if (questionDetail.questionDescription?.isNotBlank() == true) {
            questionDetail.questionDescription
        } else if (questionDetail.questionFormula?.isNotBlank() == true) {
            // With the currently used lib to show math view, use \( and \) for formulae strings.
            "\\(${questionDetail.questionFormula}\\)"
        } else {
            // Not a possible case. Ignoring.
            "NA"
        }

        when (questionDetail.questionType) {
            // TODO: Do one time operation for common fields instead of repeating in each when block.
            "1" -> {
                val optionState = parseMultipleChoiceData(questionDetail)
                return@withContext QuestionUIState(
                    question = question,
                    answerType = optionState,
                    questionTitle = UIText.StringRes(R.string.multiples_choices),
                    answerTitle = UIText.StringRes(R.string.choose_your_answer),
                    subject = questionDetail.subjectName
                )
            }
            else -> {
                return@withContext QuestionUIState(
                    question = question,
                    answerType = AnswerType.Unknown,
                    questionTitle = UIText.StringRes(R.string.question_),
                    answerTitle = UIText.StringRes(R.string.answer_),
                    subject = questionDetail.subjectName
                )
            }
        }
    }

}