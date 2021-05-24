package com.pasqualiselle.calculus

import android.util.Log
import androidx.databinding.ObservableInt
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder

class Game( ) {
    var score: Int = 0
    var currentAnswer: Int? = null
    var currentQuestion: Question = Question()
    val maxAnswer: Int = 999999999

    data class Question(val nb1: Int = (0..9).random(), val nb2: Int = (0..9).random()) {
        val expectedResult = nb1 * nb2
    }

    fun checkAnswer(): Boolean = currentAnswer == currentQuestion.expectedResult

    fun resetAnswer() {
        Log.d(TAG, "resetAnswer: now")
        currentAnswer = null
    }

    fun setQuestion() {
        val previousQuestion = currentQuestion
        while (currentQuestion == previousQuestion)
            currentQuestion = Question()
        Log.d(TAG, "setQuestion: now $currentQuestion")
    }
}
