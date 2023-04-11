package com.hrudhaykanth116.schoolmanagement.features.exam.ui.screens.questions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import com.hrudhaykanth116.schoolmanagement.R
import androidx.compose.runtime.*
import com.hrudhaykanth116.schoolmanagement.common.udf.UDFFragment
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.answeruistate.AnswerUIState
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionsState
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.bottomsheet.FilterBottomSheetFragment.Companion.KEY_FILTER_STATE
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.components.QuestionContainer
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

    override fun onCreateViewInitialization() {
        initAnswerOptionsContainer()
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

        binding.clearTV.setOnClickListener {
            sendEvent(Event.Clear)
        }

        binding.retryButton.setOnClickListener {
            // sendEvent(Event.Retry)
            Toast.makeText(requireContext(), "Not implemented. Assuming only happy path. Check Internet and reopen.", Toast.LENGTH_LONG).show()
        }

    }

    override fun processNewEffect(effect: Effect) {

    }

    override fun processNewState(state: State) {

        // TODO: Handle empty questions/error/loading case using loading, loaded, error states

        binding.progressBar.isVisible = state.isLoading
        binding.allViews.isVisible = !state.isLoading && state.errorMessage == null
        binding.errorView.isVisible = state.errorMessage != null
        binding.retryButton.isVisible = state.errorMessage != null

        if(state.errorMessage != null){
            binding.errorView.text = state.errorMessage.getText(requireContext())
        }else if (!state.isLoading) {

            // TODO: Implement recycler view
            val questionNumbers = 1..state.questionsToDisplayList.size

            val questionUIState = state.currentQuestionUIState
            binding.questionTitle.text = "${state.currentQuestionNumber}/${state.questionsToDisplayList.size}. ${state.currentQuestionUIState.questionTitle?.getText(requireContext())}"
            binding.questionContent.text = questionUIState.question
            binding.answerTitle.text = questionUIState.answerTitle?.getText(requireContext())
        }

        if (state.isInFilterMode) {
            val action = QuestionsFragmentDirections.actionLoginFragmentToSignUpFragment(
                // TODO: Assuming non null assertion
                state.filterOptionsState!!
            )
            findNavController().navigate(action)
        }

    }

    private fun initAnswerOptionsContainer() {
        binding.answerOptionsContainer.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val state = viewModel.stateFlow.collectAsState()

                val answerUIState: AnswerUIState = state.value.currentQuestionUIState.answerUIState

                QuestionContainer(
                    answersInitialUIState = answerUIState,
                    onAnswered = { newAnswerUIState: AnswerUIState ->
                        sendEvent(Event.AnswerStateChanged(newAnswerUIState))
                    }
                )
            }
        }
    }
}