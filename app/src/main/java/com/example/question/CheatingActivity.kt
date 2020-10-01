package com.example.question

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

class CheatingActivity : AppCompatActivity() {

   private lateinit var answerTextView : TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue = false
    private lateinit var api:TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheating)

        answerIsTrue =intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        api=findViewById(R.id.api)
        nametext()


        showAnswerButton.setOnClickListener {
           val  answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
        }

            answerTextView.setText(answerText)
            setAnswerShownResult(answerIsTrue)


        }


    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatingActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }


    private fun setAnswerShownResult(isAnswerShown: Boolean) {

        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)

    }

    fun nametext(){
        val version=Build.VERSION.SDK_INT
        api.text="apiLevel:$version"
    }
}