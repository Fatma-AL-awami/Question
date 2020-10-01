package com.example.question

class GroupOfQuestion {

    var questionesay = listOf(
        Question(R.string.question1, true, 0, "easy"),
        Question(R.string.question2, true, 0, "easy"),
        Question(R.string.question3, false, 0, "easy"),
        Question(R.string.question4, true, 0, "easy")
    )

    var questionmedium = listOf(
        Question(R.string.questionm1, true, 0, "med"),
        Question(R.string.questionm2, false, 0, "med"),
        Question(R.string.questionm3, false, 0, "med"),
        Question(R.string.questionm4, false, 0, "med")
    )

    var questiondif = listOf(
        Question(R.string.question_australia, true, 0, "dif"),
        Question(R.string.question_oceans, true, 0, "dif"),
        Question(R.string.question_mideast, false, 0, "dif"),
        Question(R.string.question_africa, false, 0,"dif"),
        Question(R.string.question_americas, true, 0, "dif"),
        Question(R.string.question_asia, true, 0,"dif")
    )

}

