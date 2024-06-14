package com.example.calc.appmain

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.calc.databinding.ActivityMainBinding
import com.example.calc.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.display.observe(this, Observer { value ->
            binding.display.text = value
        })

        setButtonListeners()
    }

    private fun setButtonListeners() {
        val buttons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9,
            binding.buttonAdd, binding.buttonSubtract, binding.buttonMultiply, binding.buttonDivide,
            binding.buttonEquals, binding.buttonClear, binding.buttonDelete, binding.buttonDot
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val buttonText = (it as Button).text.toString()
                viewModel.onButtonClick(buttonText)
            }
        }
    }
}
