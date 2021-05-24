package com.pasqualiselle.calculus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pasqualiselle.calculus.databinding.FragmentRunBinding


class RunFragment : Fragment() {

    private lateinit var binding: FragmentRunBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunBinding.inflate(
            layoutInflater, container, false
        )
        binding.currentGame = Game()
        Log.d(TAG, "onCreateView: the game : ${binding.currentGame}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.scoreTxt.text = getString(R.string.running_score, score)
        Log.d(TAG, "onViewCreated: the game : ${binding.currentGame}")

        with(binding) {
            val listBtn = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            listBtn.forEachIndexed() { idx, it ->
                it.setOnClickListener {
                    if (currentGame!!.currentAnswer != null) {
                        if (currentGame!!.currentAnswer!! <= (currentGame!!.maxAnswer / 10) + idx)
                            currentGame!!.currentAnswer = currentGame!!.currentAnswer!! * 10 + idx
                    } else
                        currentGame!!.currentAnswer = idx
                    invalidateAll()

                }

            }
            btnDEL.setOnClickListener {
                currentGame!!.resetAnswer()
                invalidateAll()
                    }

            btnGO.setOnClickListener {
                val result = currentGame!!.checkAnswer()
                currentGame!!.resetAnswer()
                if (result) {
                    currentGame!!.score++
                    currentGame!!.setQuestion()
                } else {
                    val action =
                        RunFragmentDirections.actionRunFragmentToGameOverFragment(currentGame!!.score)
                    findNavController().navigate(action)
                }
                invalidateAll()
            }
        }
    }
}
