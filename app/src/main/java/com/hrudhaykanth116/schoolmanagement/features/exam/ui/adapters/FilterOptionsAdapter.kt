package com.hrudhaykanth116.schoolmanagement.features.exam.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hrudhaykanth116.schoolmanagement.databinding.LayoutFilterOptionBinding
import com.hrudhaykanth116.schoolmanagement.features.exam.domain.models.FilterOptionState

class FilterOptionsAdapter(
    private val eventListener: (ItemEvent) -> Unit
) : ListAdapter<FilterOptionState, FilterOptionViewHolder>(
    DIFF_CALL_BACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterOptionViewHolder {
        return FilterOptionViewHolder(
            LayoutFilterOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterOptionViewHolder, position: Int) {
        holder.bind(getItem(position), eventListener)
    }

    companion object {

        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<FilterOptionState>() {
            override fun areItemsTheSame(
                oldItem: FilterOptionState,
                newItem: FilterOptionState
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: FilterOptionState,
                newItem: FilterOptionState
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

    sealed interface ItemEvent{
        data class CheckStateChanged(val filterOptionState: FilterOptionState): ItemEvent
    }

}