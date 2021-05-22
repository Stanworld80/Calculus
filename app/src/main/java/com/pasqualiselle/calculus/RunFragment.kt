package com.pasqualiselle.calculus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pasqualiselle.calculus.databinding.FragmentRunBinding

class RunFragment : Fragment() {

    private lateinit var binding: FragmentRunBinding
    private var answer: Int? = null
    private val maxAnswer = 999999999
    private var currentQuestion = Question()
    private var score = 0

    data class Question(val nb1: Int = (0..9).random(), val nb2: Int = (0..9).random()) {
        val expectedResult = nb1 * nb2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRunBinding.inflate(
            layoutInflater, container, false
        )
        resetAnswer()
        setQuestion()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scoreTxt.text = getString(R.string.running_score, score)
        with(binding) {
            btnDEL.setOnClickListener {
                resetAnswer()
            }
            btnGO.setOnClickListener {
                val result = checkAnswer()
                resetAnswer()
                if (result) {
                    score++
                    binding.scoreTxt.text = getString(R.string.running_score, score)
                    setQuestion()
                } else {
                    val action = RunFragmentDirections.actionRunFragmentToGameOverFragment(score)
                    findNavController().navigate(action)
                }
            }
            val listBtn = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            listBtn.forEachIndexed() { idx, it ->
                it.setOnClickListener {
                    if (answer != null) {
                        if (answer!! <= (maxAnswer / 10) + idx)
                            answer = answer!! * 10 + idx
                    } else
                        answer = idx
                    answerTxt.text = answer.toString()
                }
            }
        }
    }

    private fun resetAnswer() {
        answer = null
        binding.answerTxt.text = ""
    }

    private fun setQuestion() {
        currentQuestion = Question()
        binding.nb1.text = currentQuestion.nb1.toString()
        binding.nb2.text = currentQuestion.nb2.toString()
    }

    private fun checkAnswer(): Boolean = answer == currentQuestion.expectedResult

}