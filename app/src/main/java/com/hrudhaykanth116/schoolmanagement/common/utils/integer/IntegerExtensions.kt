package com.hrudhaykanth116.schoolmanagement.common.utils.integer

fun Int.toAlphabet(): String?{
    return if(this in 1..26){
        (this + 64).toChar().toString()
    }else{
        null
    }
}