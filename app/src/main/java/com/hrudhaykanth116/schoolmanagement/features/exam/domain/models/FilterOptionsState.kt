package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterOptionsState(
    val filterOptionsListState: List<FilterOptionState>? = null,
): Parcelable

@Parcelize
data class FilterOptionState(
    val name: String,
    val count: Int,
    // TODO: Maintaining var for now. make val
    var isChecked: Boolean = false,
): Parcelable