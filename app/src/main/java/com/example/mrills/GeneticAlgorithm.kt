package com.example.mrills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class GeneticAlgorithm : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val workView: View = inflater.inflate(R.layout.fragment_genetic_algorithm, container, false)

        val btnCalc: Button = workView.findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val tvEquation: TextView = workView.findViewById(R.id.tvEquation)
            val etA: EditText = workView.findViewById(R.id.etA)
            val etB: EditText = workView.findViewById(R.id.etB)
            val etC: EditText = workView.findViewById(R.id.etC)
            val etD: EditText = workView.findViewById(R.id.etD)
            val etY: EditText = workView.findViewById(R.id.etY)

            val a = etA.text.toString().toInt()
            val b = etB.text.toString().toInt()
            val c = etC.text.toString().toInt()
            val d = etD.text.toString().toInt()
            val y = etY.text.toString().toInt()

            val result = GeneMachine(a, b, c, d, y).solve()
            if(result.isEmpty()) {
                tvEquation.text = "No solution found"
            } else {
                tvEquation.text = "a*${result[0]} + b*${result[1]} + c*${result[2]} + d*${result[3]} = y"
            }
        }
        return workView
    }

    companion object {
        @JvmStatic
        fun newInstance() = GeneticAlgorithm()
    }
}
