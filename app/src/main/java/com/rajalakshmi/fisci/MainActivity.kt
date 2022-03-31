package com.rajalakshmi.fisci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    var isTextLocked: Boolean = false
    var isOperatorPresent: Boolean = false
    var isScientificOperatorPressed = false
    var currentOperator: String = ""
    var currentScientificOperator = ""
    var isOutputProduced: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: android.view.View) {
        if (isOutputProduced) {
            onClear()
        }
        if (!isTextLocked) {
            var digit = view.tag.toString()
            var tvInput = findViewById<TextView>(R.id.tvInput)
            tvInput.text = tvInput.text.toString() + digit
        }
    }

    fun onClear(view: android.view.View) {
        isTextLocked = false
        isOperatorPresent = false
        isScientificOperatorPressed = false
        currentOperator = ""
        currentScientificOperator = ""
        isOutputProduced = false
        var tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
    }

    fun onClear() {
        isTextLocked = false
        isOperatorPresent = false
        isScientificOperatorPressed = false
        currentOperator = ""
        currentScientificOperator = ""
        isOutputProduced = false
        var tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
    }

    fun onOperator(view: android.view.View) {
        if (isOutputProduced) {
            onClear()
        }
        var tvInput = findViewById<TextView>(R.id.tvInput)
        if (!isOperatorPresent and tvInput.text.toString().isNotEmpty()) {
            isOperatorPresent = true
            currentOperator = view.tag.toString()
            tvInput.text = tvInput.text.toString() + currentOperator
        }
    }

    fun onEqual(view: android.view.View) {
        var tvInput = findViewById<TextView>(R.id.tvInput)
        var query: List<String>? = null
        if (isScientificOperatorPressed) {
            query = tvInput.text.split("(")
            Log.i("SKHST_QUERY_455312","$query")
            if (query.size == 2) {
                var result = getScientificComputation(currentScientificOperator, query[1].toInt())
                tvInput.text = result.toString()
                isOutputProduced = true
            }
        } else if (isOperatorPresent) {
            query = tvInput.text.toString().split(currentOperator)
            Log.i("SKHST_QUERY_455312","$query")
            if (query.size == 2) {
                val num1 = query[0].toInt()
                val num2 = query[1].toInt()
                Log.i("SKHST_8451", "$num1 $num2")
                var result = computeResult(num1, num2)
                tvInput.text = result.toString()
                isOperatorPresent = false
                currentOperator = ""
                isOutputProduced = true
            }
        }
    }

    private fun computeResult(num1: Int, num2: Int): Any {
        when (currentOperator) {
            "+" -> return num1 + num2
            "-" -> return num1 - num2
            "*" -> return num1 * num2
            "/" -> return num1 / num2
            "%" -> return num1 % num2
        }
        return 0
    }

    fun OnUniScientific(view: android.view.View) {
        if (isOutputProduced) {
            onClear()
        }
        val tvInput = findViewById<TextView>(R.id.tvInput)
        var tag = view.tag.toString()
        if (tvInput.text.isNotEmpty()) {
            onClear()
        }
        tvInput.text = "$tag("
        isOperatorPresent = true
        isScientificOperatorPressed = true
        currentScientificOperator = tag

    }

    private fun getScientificComputation(tag: String, num: Int): Any {
        when (tag) {
            "sin" -> return sin(num.toDouble())
            "cos" -> return cos(num.toDouble())
            "tan" -> return tan(num.toDouble())
            "inv" -> return 1 / num
            "exp" -> return exp(num.toDouble())
            "sqrt" -> return sqrt(num.toDouble())
            "log" -> return log2(num.toDouble())
            "lan" -> return log10(num.toDouble())
        }
        return sin(num.toDouble())
    }
}