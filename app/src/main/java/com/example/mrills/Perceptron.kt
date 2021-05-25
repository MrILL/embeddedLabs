package com.example.mrills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mrills.R

class Perceptron : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val workView: View = inflater.inflate(R.layout.fragment_perceptron, container, false)

        val btnCalc: Button = workView.findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val etLearnRate: EditText = workView.findViewById(R.id.etLearnRate)
            val etDeadlineTime: EditText = workView.findViewById(R.id.etDeadlineTime)
            val etDeadlineIterations: EditText = workView.findViewById(R.id.etDeadlineIterations)

            var perceptronClass = PerceptronClass()
            perceptronClass.lRate = etLearnRate.text.toString().toDouble()
            val deadline = etDeadlineTime.text.toString().toLong()
            val iterations = etDeadlineIterations.text.toString().toInt()

            val workData: Array<Pair<Double, Double>> = arrayOf(Pair(0.0, 6.0), Pair(3.0, 3.0), Pair(1.0, 5.0), Pair(2.0, 4.0))
            val (w1: Double, w2: Double, time: Long, iter: Int) = perceptronClass.learn(workData, iterations, deadline)

            val tvPerceptronW1: TextView = workView.findViewById(R.id.tvPerceptronW1)
            val tvPerceptronW2: TextView = workView.findViewById(R.id.tvPerceptronW2)
            val tvPerceptronTime: TextView = workView.findViewById(R.id.tvPerceptronTime)
            val tvPerceptronIter: TextView = workView.findViewById(R.id.tvPerceptronIter)

            tvPerceptronW1.text = getString(R.string.perceptron_w1, w1.toString())
            tvPerceptronW2.text = getString(R.string.perceptron_w2, w2.toString())
            tvPerceptronTime.text = getString(R.string.perceptron_time, time.toString())
            tvPerceptronIter.text = getString(R.string.perceptron_iter, iter.toString())
        }
        return workView
    }

    companion object {
        @JvmStatic
        fun newInstance() = Perceptron()
    }
}