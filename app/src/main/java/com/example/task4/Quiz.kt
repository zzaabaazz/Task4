package com.example.task4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {
    private var score: Int = 0
    private var mTrueButton: Button? = null
    private var mFalseButton: Button? = null
    private var mNextButton: Button? = null
    private var mQuestionTextView: TextView? = null
    private var mQuestionBank = arrayOf<Question>(
        Question(R.string.question_android, true),
        Question(R.string.question_linear, true),
        Question(R.string.question_service, false),
        Question(R.string.question_res, true),
        Question(R.string.question_manifest, true),
Question(R.string.question_java, false),
    Question(R.string.question_activity, true),
    Question(R.string.question_intent, true),
    Question(R.string.question_fragment, true),
    Question(R.string.question_sqlite, true)

    )
    private var mCurrentIndex = 0
    private var question = 0

    companion object { //статическая переменная
        private const val KEY_INDEX = "index"
        private const val KEY_SCORE = "score"
    }
    private fun updateQuestion() {
        question = mQuestionBank[mCurrentIndex].getTextResId()
        mQuestionTextView!!.setText(question)
    }



    override fun onCreate(savedInstanceState: Bundle?) {


        /*enableEdgeToEdge()*/

        setContentView(R.layout.activity_quiz)
            super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*Toast.makeText(this, "I EXIST",Toast.LENGTH_SHORT).show()*/
        mTrueButton = findViewById<Button>(R.id.button_true)
        mFalseButton = findViewById<Button>(R.id.button_false)
        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)

        val txtScore = findViewById<TextView>(R.id.TextView)

        mTrueButton?.setOnClickListener {
            checkAnswer(true)
            txtScore.text = score.toString()
        }
        mFalseButton?.setOnClickListener {
            checkAnswer(false)
            txtScore.text = score.toString()
        }
        savedInstanceState?.let {
            mCurrentIndex = it.getInt(KEY_INDEX, 0)
            score = it.getInt(KEY_SCORE, 0)
            txtScore.text = score.toString()
        }
        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        question = mQuestionBank[mCurrentIndex].getTextResId()
        mQuestionTextView!!.setText(question)

        mNextButton = findViewById<Button>(R.id.next_button)
        mNextButton?.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            question = mQuestionBank[mCurrentIndex].getTextResId()
            mQuestionTextView!!.setText(question)
        }
        //...
        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        updateQuestion()

        //...


        mNextButton = findViewById<Button>(R.id.next_button)
        mNextButton?.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, mCurrentIndex)
        outState.putInt(KEY_SCORE, score)
    }
    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue: Boolean = mQuestionBank[mCurrentIndex].getAnswer()
        var messageResId = 0
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast
            score++
        } else messageResId = R.string.incorrect_toast
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }
}