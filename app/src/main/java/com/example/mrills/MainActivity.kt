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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalc: Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            val tvResult: TextView = findViewById(R.id.tvResult)
            val etNumber: EditText = findViewById(R.id.etNumber)
            val num: Long = etNumber.text.toString().toLong()
            tvResult.text = fermaFactors(num)

            Toast.makeText(this, "fuck", Toast.LENGTH_SHORT).show()
        }
//        button.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                // Code here executes on main thread after user presses button
//            }
//        })
    }
}