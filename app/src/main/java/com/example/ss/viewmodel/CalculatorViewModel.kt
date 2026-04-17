package com.example.ss.viewmodel
import CalculatorState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state

    fun onButtonClick(button: String) {

        when (button) {

            "C" -> {
                _state.value = CalculatorState()
            }

            "⌫" -> {
                _state.update {
                    it.copy(input = it.input.dropLast(1))
                }
            }

            "=" -> {
                try {
                    val res = eval(_state.value.input)
                    _state.update {
                        it.copy(result = res.toString())
                    }
                } catch (e: Exception) {
                    _state.update {
                        it.copy(result = "Error")
                    }
                }
            }

            else -> {
                _state.update {
                    it.copy(input = it.input + button)
                }
            }
        }
    }
}
fun eval(expression: String): Double {
    return object : Any() {
        var pos = -1
        var ch = 0

        fun nextChar() {
            ch = if (++pos < expression.length) expression[pos].code else -1
        }

        fun eat(charToEat: Int): Boolean {
            while (ch == ' '.code) nextChar()
            if (ch == charToEat) {
                nextChar()
                return true
            }
            return false
        }

        fun parse(): Double {
            nextChar()
            val x = parseExpression()
            return x
        }

        fun parseExpression(): Double {
            var x = parseTerm()
            while (true) {
                when {
                    eat('+'.code) -> x += parseTerm()
                    eat('-'.code) -> x -= parseTerm()
                    else -> return x
                }
            }
        }

        fun parseTerm(): Double {
            var x = parseFactor()
            while (true) {
                when {
                    eat('*'.code) -> x *= parseFactor()
                    eat('/'.code) -> x /= parseFactor()
                    else -> return x
                }
            }
        }

        fun parseFactor(): Double {
            if (eat('+'.code)) return parseFactor()
            if (eat('-'.code)) return -parseFactor()

            var x: Double
            val startPos = pos

            if (ch in '0'.code..'9'.code || ch == '.'.code) {
                while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                x = expression.substring(startPos, pos).toDouble()
            } else {
                throw RuntimeException("Unexpected")
            }

            return x
        }
    }.parse()
}