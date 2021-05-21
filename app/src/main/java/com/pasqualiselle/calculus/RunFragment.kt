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
    private var answer  = 0
    private val maxAnswer = 999999999

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRunBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btnDEL.setOnClickListener {
                answer = 0
                answerTxt.text = ""
            }
            val listBtn = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            listBtn.forEachIndexed() { idx, it ->
                it.setOnClickListener {
                    if (answer <= (maxAnswer / 10)+idx) {
                        answer = answer * 10 + idx
                        answerTxt.text = answer.toString()
                    }
                }
            }
        }
    }
}