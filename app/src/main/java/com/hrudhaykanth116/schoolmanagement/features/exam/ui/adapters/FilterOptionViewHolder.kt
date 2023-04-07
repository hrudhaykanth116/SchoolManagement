package com.hrudhaykanth116.schoolmanagement.features.exam.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.hrudhaykanth116.schoolmanagement.databinding.LayoutFilterOptionBinding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOption

class FilterOptionViewHolder(
    private val binding: LayoutFilterOptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        filterOption: FilterOption,
        eventListener: (FilterOptionsAdapter.ItemEvent) -> Unit
    ) {

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->

            filterOption.isChecked = isChecked

            eventListener.invoke(FilterOptionsAdapter.ItemEvent.CheckStateChanged(filterOption))
        }
        binding.label.text = "${filterOption.name} (${filterOption.count})"

    }

}