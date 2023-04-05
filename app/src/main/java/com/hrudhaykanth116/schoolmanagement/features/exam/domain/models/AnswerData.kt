package com.hrudhaykanth116.schoolmanagement.features.exam.domain.models

data class AnswerData(
    val optionId: Int,
    val description: String,
    // Formulae may not be needed because optionId would be enough in this case
    val formulae: String,
)
