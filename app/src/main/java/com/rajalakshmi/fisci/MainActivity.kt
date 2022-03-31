package com.rajalakshmi.fisci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var isTextLocked : Boolean = false
    var isOperatorPresent : Boolean = false
    var currentOperator : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: android.view.View) {
        if(!isTextLocked){
            var tvInput = findViewById<TextView>(R.id.tvInput)
            var digit = view.tag.toString()
            tvInput.text = tvInput.text.toString() + digit
        }
    }
    fun onClear(view: android.view.View) {
        isTextLocked = false
        isOperatorPresent = false
        currentOperator = ""
        var tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
    }
    fun onOperator(view: android.view.View) {
        var tvInput = findViewById<TextView>(R.id.tvInput)
        if(!isOperatorPresent and tvInput.text.toString().isNotEmpty()){
            isOperatorPresent = true
            currentOperator = view.tag.toString()
            tvInput.text = tvInput.text.toString() + currentOperator
        }
    }
    fun onEqual(view: android.view.View) {
        var tvInput = findViewById<TextView>(R.id.tvInput)
        var query : List<String> = tvInput.text.toString().split(currentOperator)

        val num1 = query[0].toInt()
        val num2 = query[1].toInt()
        Log.i("SKHST_8451","$num1 $num2")
        var result = computeResult(num1,num2)

        tvInput.text = result.toString()

    }

    private fun computeResult(num1: Int, num2: Int): Any {
        when(currentOperator){
            "+" -> return num1 + num2
            "-" -> return num1 - num2
            "*" -> return num1 * num2
            "/" -> return num1 / num2
            "%" -> return num1 % num2
        }
        return 0
    }
}