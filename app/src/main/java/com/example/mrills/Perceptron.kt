package com.example.mrills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class Perceptron : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val workView: View = inflater.inflate(R.layout.fragment_perceptron, container, false)

        val pgWait: ProgressBar = workView.findViewById(R.id.pgWait)
        pgWait.visibility = View.INVISIBLE

        val btnCalc: Button = workView.findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val etLearnRate: EditText = workView.findViewById(R.id.etLearnRate)
            val etDeadlineTime: EditText = workView.findViewById(R.id.etDeadlineTime)
            val etDeadlineIterations: EditText = workView.findViewById(R.id.etDeadlineIterations)
            val pgWait: ProgressBar = workView.findViewById(R.id.pgWait)
            pgWait.visibility = View.VISIBLE

            Thread(Runnable {
                getActivity()?.runOnUiThread(Runnable {
                    var perceptronClass = PerceptronClass()
                    perceptronClass.lRate = etLearnRate.text.toString().toDouble()
                    val deadline = etDeadlineTime.text.toString().toLong()
                    val iterations = etDeadlineIterations.text.toString().toInt()

                    val workData: Array<Pair<Double, Double>> = arrayOf(Pair(0.0, 6.0), Pair(3.0, 3.0), Pair(1.0, 5.0), Pair(2.0, 4.0))
                    val (w1: Double, w2: Double, time: Long, iter: Int) = perceptronClass.learn(workData, iterations, deadline)
                    pgWait.visibility = View.INVISIBLE


                    val tvPerceptronW1: TextView = workView.findViewById(R.id.tvPerceptronW1)
                    val tvPerceptronW2: TextView = workView.findViewById(R.id.tvPerceptronW2)
                    val tvPerceptronTime: TextView = workView.findViewById(R.id.tvPerceptronTime)
                    val tvPerceptronIter: TextView = workView.findViewById(R.id.tvPerceptronIter)

                    tvPerceptronW1.text = getString(R.string.perceptron_w1, w1.toString())
                    tvPerceptronW2.text = getString(R.string.perceptron_w2, w2.toString())
                    tvPerceptronTime.text = getString(R.string.perceptron_time, time.toString())
                    tvPerceptronIter.text = getString(R.string.perceptron_iter, iter.toString())
                })
            }).start()
        }
        return workView
    }

    companion object {
        @JvmStatic
        fun newInstance() = Perceptron()
    }
}