package com.example.mrills

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil
import kotlin.math.sqrt

fun formatResult(num1: Long, num2: Long): String {
    return "${num1.toString()}, ${num2.toString()}"
}

fun formatResultIter(i: Long): String {
    return "iterations: ${i.toString()}"
}

fun fermaFactors(n: Long): Array<String> {
    if(n <= 0) {
        return arrayOf(n.toString(), formatResultIter(0))
    }

    if(n % 2 == 0L) {
        return arrayOf(formatResult(n/2, n), formatResultIter(0))
    }

    var a: Long = ceil(sqrt(n.toDouble())).toLong()
    if(a * a == n) {
        return arrayOf(formatResult(a, a), formatResultIter(0))
    }

    var b: Long
    var i: Long = 0
    while(true) {
        val b1: Long = a * a - n
        b = sqrt(b1.toDouble()).toLong()
        if(b * b == b1) {
            break
        } else {
            a += 1
        }
        i++
    }
    return arrayOf(formatResult(a - b, a + b), formatResultIter(i))
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalc: Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val tvResult: TextView = findViewById(R.id.tvResult)
            val etNumber: EditText = findViewById(R.id.etNumber)
            val num: Long = etNumber.text.toString().toLong()
            val tvResultIter: TextView = findViewById(R.id.tvResultIter)

            val res = fermaFactors(num)

            tvResult.text = res[0]
            tvResultIter.text = res[1]
        }
//        button.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                // Code here executes on main thread after user presses button
//            }
//        })
    }
}