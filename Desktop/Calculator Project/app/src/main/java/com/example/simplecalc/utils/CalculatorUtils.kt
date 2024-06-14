package com.example.simplecalc.utils

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

object CalculatorUtils {

    fun evaluate(expression: String): Double {
        val context = Context.enter()
        return try {
            context.optimizationLevel = -1
            val scope: Scriptable = context.initStandardObjects()
            val result = context.evaluateString(scope, expression, "JavaScript", 1, null)
            result as Double
        } finally {
            Context.exit()
        }
    }
}
