package com.pasqualiselle.calculus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pasqualiselle.calculus.databinding.FragmentGameOverBinding


class GameOverFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args  = GameOverFragmentArgs.fromBundle(requireArguments())
        val binding = FragmentGameOverBinding.inflate(layoutInflater, container, false)
        binding.resultTxt.text = getString(R.string.final_score, args.score)
        return binding.root
    }

}