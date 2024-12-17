package com.example.task4

class Question(private var mTextResId: Int, private var mAnswerTrue: Boolean) {
    fun getTextResId(): Int {
        return mTextResId
    }
    fun getAnswer(): Boolean {
        return mAnswerTrue
    }
}