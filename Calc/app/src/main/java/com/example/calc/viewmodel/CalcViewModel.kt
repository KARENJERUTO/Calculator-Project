package com.example.calc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val _display = MutableLiveData<String>().apply { value = "0" }
    val display: LiveData<String> = _display

    private var currentInput = StringBuilder()
    private var result = 0.0
    private var lastOperator = ""
    private var computationString = StringBuilder()

    fun onButtonClick(buttonText: String) {
        when (buttonText) {
            in "0123456789" -> onDigitClick(buttonText)
            "+", "-", "*", "/" -> onOperatorClick(buttonText)
            "=" -> onEqualsClick()
            "C" -> onClearClick()
            "Del" -> onDeleteClick()
            "." -> onDotClick()
        }
    }

    private fun onDigitClick(digit: String) {
        if (currentInput.length == 1 && currentInput[0] == '0') {
            currentInput.clear()
        }
        currentInput.append(digit)
        updateDisplay(computationString.toString() + currentInput.toString())
    }

    private fun onOperatorClick(operator: String) {
        if (currentInput.isNotEmpty()) {
            calculate()
            lastOperator = operator
            currentInput.clear()
            updateComputationString(operator)
            updateDisplay(computationString.toString())
        }
    }

    private fun onEqualsClick() {
        if (currentInput.isNotEmpty()) {
            calculate()
            updateDisplay(result.toString())
            currentInput.clear()
            lastOperator = ""
        }
    }

    private fun onClearClick() {
        currentInput.clear()
        result = 0.0
        lastOperator = ""
        computationString.clear()
        updateDisplay("0")
    }

    private fun onDeleteClick() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay(computationString.toString() + currentInput.toString())
        }
    }

    private fun onDotClick() {
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput.append("0.")
            } else {
                currentInput.append(".")
            }
            updateDisplay(computationString.toString() + currentInput.toString())
        }
    }

    private fun calculate() {
        if (currentInput.isNotEmpty()) {
            val currentValue = currentInput.toString().toDouble()
            when (lastOperator) {
                "+" -> result += currentValue
                "-" -> result -= currentValue
                "*" -> result *= currentValue
                "/" -> {
                    if (currentValue != 0.0) {
                        result /= currentValue
                    } else {
                        _display.value = "Error: Division by zero"
                        return
                    }
                }
                else -> result = currentValue
            }
            computationString.append(currentInput.toString())
            currentInput.clear()
        }
    }

    private fun updateDisplay(value: String) {
        _display.value = value
    }

    private fun updateComputationString(newInput: String) {
        if (newInput in "+-*/") {
            computationString.append(" $newInput ")
        } else {
            computationString.append(newInput)
        }
    }
}
