package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var currentCalculation:TextView
    private lateinit var previousCalculation:TextView

    private var currentNumber = 0.0
    private var currentOperation = ""
    private var isNewOperation = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        currentCalculation = findViewById(R.id.currentCalculationTextView)
        previousCalculation = findViewById(R.id.previousCalculationTextView)

        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val equalButton = findViewById<Button>(R.id.equalButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val backspaceButton = findViewById<Button>(R.id.backspaceButton)
        val dotButton = findViewById<Button>(R.id.dotButton)
        val percentageButton = findViewById<Button>(R.id.percentageButton)

//        NUMBER BUTTON CLICK LISTENER
        button0.setOnClickListener {appendNumber("0")}
        button1.setOnClickListener {appendNumber("1")}
        button2.setOnClickListener {appendNumber("2")}
        button3.setOnClickListener {appendNumber("3")}
        button4.setOnClickListener {appendNumber("4")}
        button5.setOnClickListener {appendNumber("5")}
        button6.setOnClickListener {appendNumber("6")}
        button7.setOnClickListener {appendNumber("7")}
        button8.setOnClickListener {appendNumber("8")}
        button9.setOnClickListener {appendNumber("9")}
        dotButton.setOnClickListener {appendNumber(".")}

        percentageButton.setOnClickListener{setOperation("%")}
        addButton.setOnClickListener{setOperation("+")}
        subtractButton.setOnClickListener{setOperation("-")}
        multiplyButton.setOnClickListener{setOperation("*")}
        divideButton.setOnClickListener{setOperation("/")}
        equalButton.setOnClickListener{calculateResult()}
        clearButton.setOnClickListener{clearCalculator()}
        backspaceButton.setOnClickListener{backspace()}
    }

    private fun backspace() {
        currentCalculation.text = currentCalculation.text.toString().dropLast(1)
    }

    private fun clearCalculator() {
        currentCalculation.text = "0"
        previousCalculation.text = ""
        currentNumber = 0.0
        isNewOperation = true
    }

    private fun calculateResult() {
        try {
            val secondNumber: Double = currentCalculation.text.toString().toDouble()
            val result = when (currentOperation) {
                "+" -> currentNumber + secondNumber
                "-" -> currentNumber - secondNumber
                "*" -> currentNumber * secondNumber
                "/" -> currentNumber / secondNumber
                "%" -> currentNumber % secondNumber
                else -> secondNumber
            }
            previousCalculation.text = "$currentNumber $currentOperation $secondNumber ="
            currentCalculation.text = result.toString()
            currentNumber = result
            isNewOperation = true

        }
        catch (e:Exception){
            currentCalculation.text = "Error"
        }


    }

    private fun setOperation(operation:String){
        currentNumber = currentCalculation.text.toString().toDouble()
        currentOperation = operation
        isNewOperation = true
        previousCalculation.text = "$currentNumber $currentOperation"
    }

    private fun appendNumber(number:String){
        if(isNewOperation){
            currentCalculation.text = ""
            isNewOperation = false
        }
        else{
            currentCalculation.text = "${currentCalculation.text}$number"

        }
    }
}