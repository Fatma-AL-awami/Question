package com.example.question

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.random.Random.Default.nextInt

class QuizViewModel: ViewModel() {

    var questionBank = mutableListOf<Question>()

    var currentIndex = 0
    var isCheater = false
    var isattemp=3;

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var  currentQuestionstate: Int = 0
        get() = questionBank[currentIndex].result

    var  currentQuestiontype: String = ""
        get() = questionBank[currentIndex].type_ques

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }


    }


