package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFFragment
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.AnswerData
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOption
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionsState
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.bottomsheet.FilterBottomSheetFragment.Companion.KEY_FILTER_STATE
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.QuestionOptionsContainer
import dagger.hilt.android.AndroidEntryPoint
import com.hrudhaykanth116.schoolmanagement.databinding.FragmentQuestionsBinding as Binding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEffect as Effect
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.QuestionsScreenEvent as Event
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.ExamUIState as State

@AndroidEntryPoint
class QuestionsFragment : UDFFragment<State, Event, Effect, Binding>(
    R.layout.fragment_questions
) {
    override val viewModel: QuestionsViewModel by viewModels()

    override fun initViews() {
        setClickListeners()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserverForFilterFragment()
    }

    private fun setObserverForFilterFragment() {
        val navBackStackEntry = findNavController().getBackStackEntry(R.id.questionsFragment)

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {

                // TODO: Exit filter mode properly with an event. Use backstack changed listener from nav host to set filter mode state
                sendEvent(Event.Filter(viewModel.state.filterOptionsState))

                if (navBackStackEntry.savedStateHandle.contains(KEY_FILTER_STATE)) {
                    val result: FilterOptionsState? =
                        navBackStackEntry.savedStateHandle.get<FilterOptionsState>(KEY_FILTER_STATE)

                    result?.let {
                        sendEvent(Event.Filter(it))
                    }
                }


            }
        }
        navBackStackEntry.getLifecycle().addObserver(observer)

        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.getLifecycle().removeObserver(observer)
            }
        })
    }

    private fun setClickListeners() {
        binding.nextButton.setOnClickListener {
            sendEvent(Event.Next)
        }
        binding.prevButton.setOnClickListener {
            sendEvent(Event.Prev)
        }

        binding.filterIcon.setOnClickListener {
            sendEvent(Event.FilterIconClicked)
        }

    }

    override fun processNewEffect(effect: Effect) {

    }

    override fun processNewState(state: State) {

        // TODO: Handle empty questions/error/loading case using loading, loaded, error states

        binding.progressBar.isVisible = state.isLoading

        if (!state.isLoading) {
            val questionUIState = state.currentQuestionUIState
            binding.questionTitle.text = state.currentQuestionNumber.toString()
            binding.questionContent.text = questionUIState?.question
            binding.answerTitle.text = questionUIState?.answerTitle?.getText(requireContext())

            // Added previous button for testing purpose
            binding.prevButton.isVisible = true

            binding.answerOptionsContainer.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    QuestionOptionsContainer(
                        optionState = state.currentQuestionUIState?.answerType!!,
                        onAnswered = { answer ->
                            onAnswered(answer)
                        },
                        answerData = state.currentQuestionUIState.answerData
                    )
                }
            }

        }

        if(state.isInFilterMode){
            val action = QuestionsFragmentDirections.actionLoginFragmentToSignUpFragment(
                // TODO: Assuming non null assertion
                state.filterOptionsState!!
            )
            findNavController().navigate(action)
        }

    }

    private fun onAnswered(answerData: AnswerData){
        sendEvent(event = Event.Answered(answerData))
    }
}