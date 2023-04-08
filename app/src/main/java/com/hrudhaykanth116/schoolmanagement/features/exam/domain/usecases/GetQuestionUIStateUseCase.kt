package com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases

import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.common.ui.models.UIText
import com.hrudhaykanth116.schoolmanagement.features.exam.data.models.network.GetExamDataResponse
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.usecases.answerdataparsers.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuestionUIStateUseCase @Inject constructor(
    private val parseMultipleChoiceData: ParseMultipleChoiceData,
    private val parseFillInTheBlackData: ParseFillInTheBlackData,
    private val parseEasyAnswersData: ParseEasyAnswersData,
    private val parseMultipleAnswersData: ParseMultipleAnswersData,
    private val parseTrueFalseAnswersData: ParseTrueFalseAnswersData,
    private val parseShortAnswerData: ParseShortAnswerData,
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

        val questionUIState: QuestionUIState = when (questionDetail.questionType) {
            // TODO: Do one time operation for common fields instead of repeating in each when block.
            "1" -> {
                val optionState = parseMultipleChoiceData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.multiples_choices),
                    answerTitle = UIText.StringRes(R.string.choose_your_answer),
                )
            }
            "2" -> {
                val optionState = parseTrueFalseAnswersData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.true_false),
                    answerTitle = UIText.StringRes(R.string.choose_one),
                )
            }
            "3" -> {
                val optionState = parseShortAnswerData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.short_answer),
                    answerTitle = UIText.StringRes(R.string.enter_short_answer),
                )
            }
            "4" -> {
                val optionState = parseFillInTheBlackData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.fill_in_the_blank),
                    answerTitle = UIText.StringRes(R.string.enter_answer),
                )
            }
            "5" -> {
                val optionState = parseEasyAnswersData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.easy_answer),
                    answerTitle = UIText.StringRes(R.string.enter_the_essay),
                )
            }
            "6" -> {
                val optionState = parseMultipleAnswersData(questionDetail)
                QuestionUIState(
                    answerUIState = optionState,
                    questionTitle = UIText.StringRes(R.string.multiple_answers),
                    answerTitle = UIText.StringRes(R.string.choose_single_or_multiple_options),
                )
            }
            else -> {
                QuestionUIState(
                    answerUIState = AnswerUIState.Unknown(questionDetail.questionId.toString()),
                    questionTitle = UIText.StringRes(R.string.question_),
                    answerTitle = UIText.StringRes(R.string.answer_),
                )
            }
        }

        return@withContext questionUIState.copy(
            questionId = questionDetail.questionId?.toString(),
            question = question,
            subject = questionDetail.subjectName
        )
    }

}