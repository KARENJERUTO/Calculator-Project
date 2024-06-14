package com.example.simplecalc.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.simplecalc.databinding.ActivityMainBinding
import com.example.simplecalc.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply edge-to-edge display
        enableEdgeToEdge()

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up button click listeners
        binding.btn0.setOnClickListener { appendExpression("0") }
        binding.btn1.setOnClickListener { appendExpression("1") }
        binding.btn2.setOnClickListener { appendExpression("2") }
        binding.btn3.setOnClickListener { appendExpression("3") }
        binding.btn4.setOnClickListener { appendExpression("4") }
        binding.btn5.setOnClickListener { appendExpression("5") }
        binding.btn6.setOnClickListener { appendExpression("6") }
        binding.btn7.setOnClickListener { appendExpression("7") }
        binding.btn8.setOnClickListener { appendExpression("8") }
        binding.btn9.setOnClickListener { appendExpression("9") }
        binding.btnAdd.setOnClickListener { appendExpression("+") }
        binding.btnSubtract.setOnClickListener { appendExpression("-") }
        binding.btnMultiply.setOnClickListener { appendExpression("*") }
        binding.btnDivide.setOnClickListener { appendExpression("/") }
        binding.btnClear.setOnClickListener { clearExpression() }
        binding.btnEquals.setOnClickListener { calculateResult() }


        // Observe the ViewModel
        viewModel.expression.observe(this, { expression ->
            binding.tvExpression.text = expression
        })

        viewModel.result.observe(this, { result ->
            binding.tvResult.text = result
        })
    }

    private fun appendExpression(value: String) {
        viewModel.appendExpression(value)
    }

    private fun clearExpression() {
        viewModel.clearExpression()
    }

    private fun calculateResult() {
        viewModel.calculateResult()
    }
}
