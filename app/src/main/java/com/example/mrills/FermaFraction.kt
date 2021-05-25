package com.example.mrills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ceil
import kotlin.math.sqrt

fun formatResult(num1: Long, num2: Long): String {
    return "${num1.toString()}, ${num2.toString()}"
}

fun fermaFactors(n: Long): String {
    if(n <= 0) {
        return n.toString()
    }

    if(n % 2 == 0L) {
        return formatResult(n/2, n)
    }

    var a: Long = ceil(sqrt(n.toDouble())).toLong()
    if(a * a == n) {
        return formatResult(a, a)
    }

    var b: Long
    while(true) {
        val b1: Long = a * a - n
        b = sqrt(b1.toDouble()).toLong()
        if(b * b == b1) {
            break
        } else {
            a += 1
        }
    }
    return formatResult(a - b, a + b)
}

class FermaFraction : Fragment(R.layout.fragment_ferma_fraction) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_ferma_fraction, container, false)

        val btnCalc: Button = root.findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val tvResult: TextView = root.findViewById(R.id.tvResult)
            val etNumber: EditText = root.findViewById(R.id.etNumber)
            val num: Long = etNumber.text.toString().toLong()
            tvResult.text = fermaFactors(num)
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FermaFraction()
    }
}