package com.example.simplecalc.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplecalc.utils.CalculatorUtils


class CalculatorViewModel : ViewModel() {

    private val _expression = MutableLiveData<String>().apply { value = "" }
    val expression: LiveData<String> get() = _expression

    private val _result = MutableLiveData<String>().apply { value = "" }
    val result: LiveData<String> get() = _result

    fun appendExpression(value: String) {
        _expression.value += value
    }

    fun clearExpression() {
        _expression.value = ""
        _result.value = ""
    }

    fun calculateResult() {
        val expr = _expression.value
        if (!expr.isNullOrEmpty()) {
            try {
                _result.value = CalculatorUtils.evaluate(expr).toString()
            } catch (e: Exception) {
                _result.value = "Error"
            }
        }
    }
}
