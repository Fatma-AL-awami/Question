package com.example.question

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random.Default.nextInt

private const val REQUEST_CODE_CHEAT = 0
class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    var groupOfQuestion = GroupOfQuestion()

    // var groupOfQuestion = listOf <Question>()
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var tot: TextView
    private lateinit var cheatButton: Button
    private lateinit var attempet:TextView

    var tru = 0;
    var fals = 0;
    var test = 0;
    var res_answer = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_description)
        prevButton = findViewById(R.id.prev_description)
        questionTextView = findViewById(R.id.question_text_view);
        cheatButton = findViewById(R.id.cheat_button)
        tot = findViewById(R.id.textView)
        attempet=findViewById(R.id.att)
        getRandomQuote()



        cheatButton.setOnClickListener { view: View ->
            attempet.text = "Number of attempts :" +quizViewModel.isattemp.toString();
            quizViewModel.isattemp-=1;
            if(quizViewModel.isattemp<1) {
                cheatButton.isEnabled=false

            }
                attempet.text = "Number of attempts :" +quizViewModel.isattemp.toString();
            // Start CheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
           val intent = CheatingActivity.newIntent(this@MainActivity,answerIsTrue )
            startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        trueButton.setOnClickListener { view: View ->
            quizViewModel.questionBank[quizViewModel.currentIndex].result = 1
            trueButton.isEnabled = false;
            falseButton.isEnabled = false;
            checkAnswer(true)
            The_Result()
            num_ques()

        }

        falseButton.setOnClickListener { view: View ->
            trueButton.isEnabled = false;
            falseButton.isEnabled = false;
            quizViewModel.questionBank[quizViewModel.currentIndex].result = 1
            checkAnswer(false)
            The_Result()
            num_ques()

        }
        updateQuestion()

        nextButton.setOnClickListener {
            quizViewModel.isCheater=false

            if (quizViewModel.currentIndex == 5) {
                nextButton.isEnabled = false

            } else {
                if (quizViewModel.questionBank[quizViewModel.currentIndex + 1].result == 0) {
                    trueButton.isEnabled = true;
                    falseButton.isEnabled = true
                }
                quizViewModel.moveToNext();
                updateQuestion()
            }
        }

        prevButton.setOnClickListener {

            if (quizViewModel.currentIndex == 0) {
                quizViewModel.currentIndex = 1;
            }

            if (quizViewModel.currentIndex != 0) {
                if (quizViewModel.questionBank[quizViewModel.currentIndex - 1].result == 0) {
                    trueButton.isEnabled = true;
                    falseButton.isEnabled = true
                }

                quizViewModel.currentIndex = if (quizViewModel.currentIndex == 0) {
                    quizViewModel.currentIndex + 1
                } else {
                    quizViewModel.currentIndex - 1
                }

                updateQuestion()
            }
        }
    }


    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    }


    private fun checkAnswer(userAnswer: Boolean) {
        var messageResId = ""
        val correctAnswer = quizViewModel.currentQuestionAnswer
        var cheater = quizViewModel.isCheater
        if (cheater == true){

           messageResId="judgment_toast"
        }
        else
        {
         if (userAnswer == correctAnswer) {
            tru += 1;
            when (quizViewModel.currentIndex) {
                0, 1 -> res_answer += 2;
                2, 3 -> res_answer += 4;
                4, 5 -> res_answer += 6;
            }

            tot.text = "The Result : " + res_answer.toString();
             messageResId=  "  Correct  "


        } else if (userAnswer != correctAnswer) {

            fals += 1
             messageResId= "InCorrect"
        }

            }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    fun The_Result() {

        if (quizViewModel.questionBank[quizViewModel.currentIndex].result == 1) {
            test += 1
        }

        text1.text =
            "Number of question : " + test + "   Correct answers : " + tru + "   Wrong answers :" + fals;
    }

    fun num_ques() {
        if (quizViewModel.currentIndex == quizViewModel.questionBank.size) {
            nextButton.isEnabled = false;
            prevButton.isEnabled = false
        }
    }

    fun getRandomQuote() {

        quizViewModel.questionBank.clear();
        quizViewModel.currentIndex = 0;
        for (i in 1..2) {

            var randomValue = Random().nextInt(groupOfQuestion.questionesay.size)
            quizViewModel.questionBank.add(groupOfQuestion.questionesay[randomValue])

        }


        for (i in 1..2) {
            val randomValue1 = Random().nextInt(groupOfQuestion.questionmedium.size)
            quizViewModel.questionBank.add(groupOfQuestion.questionmedium[randomValue1])
        }
        for (i in 1..2) {

            val randomValue2 = Random().nextInt(groupOfQuestion.questiondif.size)
            quizViewModel.questionBank.add(groupOfQuestion.questiondif[randomValue2])
        }

    }



   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater =true
                //data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }
}


