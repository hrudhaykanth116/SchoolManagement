package com.hrudhaykanth116.schoolmanagement.features.exam.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.hrudhaykanth116.schoolmanagement.databinding.LayoutFilterOptionBinding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionState

class FilterOptionViewHolder(
    private val binding: LayoutFilterOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        filterOptionState: FilterOptionState,
        eventListener: (FilterOptionsAdapter.ItemEvent) -> Unit
    ) {

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->

            filterOptionState.isChecked = isChecked

            eventListener.invoke(FilterOptionsAdapter.ItemEvent.CheckStateChanged(filterOptionState))
        }
        binding.label.text = "${filterOptionState.name} (${filterOptionState.count})"
        binding.checkbox.isChecked = filterOptionState.isChecked

    }

}