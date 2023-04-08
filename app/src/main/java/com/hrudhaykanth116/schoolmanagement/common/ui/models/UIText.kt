package com.hrudhaykanth116.schoolmanagement.common.ui.models

import android.content.Context

sealed class UIText {

    class StringRes(@androidx.annotation.StringRes val stringRes: Int, vararg val formatArgs: Any) : UIText()
    data class Text(val rawString: String): UIText()

    fun getText(context: Context): String{
        return when(this){
            is StringRes -> {
                context.getString(stringRes, formatArgs)
            }
            is Text -> {
                rawString
            }
        }
    }
}