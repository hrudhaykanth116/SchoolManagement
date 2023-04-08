package com.hrudhaykanth116.schoolmanagement.features.exam.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hrudhaykanth116.schoolmanagement.databinding.FragmentFilterOptionsBinding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionState
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.adapters.FilterOptionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterBottomSheetFragment: BottomSheetDialogFragment() {

    lateinit var binding: FragmentFilterOptionsBinding

    val args: FilterBottomSheetFragmentArgs by navArgs()

    private val filterOptionState by lazy {
        args.filterOptionState
    }

    // TODO: This list will be maintained to return selected filters when clicked on apply
    //  and actual filter state is not disturbed.
    private var resultFilterOptionStates: List<FilterOptionState> = listOf()

    private val adapter by lazy {
        FilterOptionsAdapter{ event: FilterOptionsAdapter.ItemEvent ->

            when (event) {
                is FilterOptionsAdapter.ItemEvent.CheckStateChanged -> {
                    resultFilterOptionStates.find {
                        event.filterOptionState == it
                    }?.isChecked = event.filterOptionState.isChecked
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentFilterOptionsBinding.inflate(
            inflater
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Handle outside clicked event

        resultFilterOptionStates = filterOptionState.filterOptionsListState ?: listOf()

        binding.optionsList.adapter = adapter

        adapter.submitList(
            filterOptionState.filterOptionsListState
        )

        binding.clearButton.setOnClickListener {

            val clearedList = ArrayList(resultFilterOptionStates.map {
                it.copy(
                    isChecked = false
                )
            })

            resultFilterOptionStates = clearedList

            adapter.submitList(
                resultFilterOptionStates
            )
        }

        binding.applyButton.setOnClickListener {

            val newFilterState = filterOptionState.copy(
                filterOptionsListState = resultFilterOptionStates
            )

            findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_FILTER_STATE, newFilterState)
            dismiss()
        }

    }

    companion object{
        private const val TAG = "FilterBottomSheetFragme"
        const val KEY_FILTER_STATE = "key_filter_state"
    }

}