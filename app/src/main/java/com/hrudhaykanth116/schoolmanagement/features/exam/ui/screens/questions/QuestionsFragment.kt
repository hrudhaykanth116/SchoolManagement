package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFFragment
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.QuestionOptionsContainer
import dagger.hilt.android.AndroidEntryPoint
import com.hrudhaykanth116.schoolmanagement.databinding.FragmentQuestionsBinding as Binding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEffect as Effect
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent as Event
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenUIState as State

@AndroidEntryPoint
class QuestionsFragment : UDFFragment<State, Event, Effect, Binding>(
    R.layout.fragment_questions
) {
    override val viewModel: QuestionsViewModel by viewModels()

    override fun initViews() {
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.nextButton.setOnClickListener {
            sendEvent(Event.Next)
        }
        binding.prevButton.setOnClickListener {
            sendEvent(Event.Prev)
        }
    }

    override fun processNewEffect(effect: Effect) {

    }

    override fun processNewState(state: State) {

        // val actualFromState = "\\(${state.question}\\)"

        binding.questionTitle.text = state.questionNumber.toString()
        binding.questionContent.text = state.question
        binding.answerTitle.text = state.answerTitle?.getText(requireContext())

        // Added previous button for testing purpose
        binding.prevButton.isVisible = false

        binding.answerOptionsContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                QuestionOptionsContainer(
                    optionState = state.questionOptions
                )
            }
        }
    }
}