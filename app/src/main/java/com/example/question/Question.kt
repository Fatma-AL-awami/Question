package com.example.question

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean,var result:Int, var  type_ques:String)
{

}

