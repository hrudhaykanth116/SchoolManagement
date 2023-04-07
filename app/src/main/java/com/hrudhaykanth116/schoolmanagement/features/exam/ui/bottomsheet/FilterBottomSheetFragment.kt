package com.hrudhaykanth116.schoolmanagement.features.exam.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hrudhaykanth116.schoolmanagement.databinding.FragmentFilterOptionsBinding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOption
import com.hrudhaykanth116.schoolmanagement.features.exam.ui.adapters.FilterOptionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterBottomSheetFragment: BottomSheetDialogFragment() {

    lateinit var binding: FragmentFilterOptionsBinding

    val args: FilterBottomSheetFragmentArgs by navArgs()

    private val filterOptionState by lazy {
        args.filterOptionState
    }

    private var resultFilterOptions: List<FilterOption> = listOf()

    private val adapter by lazy {
        FilterOptionsAdapter{ event: FilterOptionsAdapter.ItemEvent ->

            when (event) {
                is FilterOptionsAdapter.ItemEvent.CheckStateChanged -> {
                    resultFilterOptions.find { event.filterOption == it }?.isChecked = event.filterOption.isChecked
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

        resultFilterOptions = filterOptionState.filterOptionsList ?: listOf()

        binding.optionsList.adapter = adapter

        adapter.submitList(
            filterOptionState.filterOptionsList
        )

        binding.clearButton.setOnClickListener {

            val clearedList = ArrayList(resultFilterOptions.map {
                it.copy(
                    isChecked = false
                )
            })

            resultFilterOptions = clearedList

            adapter.submitList(
                resultFilterOptions
            )
        }

        binding.applyButton.setOnClickListener {

            val newFilterState = filterOptionState.copy(
                filterOptionsList = resultFilterOptions
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