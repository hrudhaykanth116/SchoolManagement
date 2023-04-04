package com.hrudhaykanth116.schoolmanagement.common.ui.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.hrudhaykanth116.schoolmanagement.R
import com.hrudhaykanth116.schoolmanagement.databinding.ViewMyMathBinding

class MyMathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewMyMathBinding

    init {
        // context.withStyledAttributes(attrs, R.styleable.MyMathView) {
        //     val initialText = getText(R.styleable.MyMathView_text)
        // }
        binding = ViewMyMathBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var text: String? = null
        set(value) {
            if(field != value){
                binding.mathRenderView.text = value
                field = value
            }
        }

    companion object{
        private const val TAG = "MyMathView"
    }


}