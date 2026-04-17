package com.example.ss.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ss.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel()
) {

    val state = viewModel.state.collectAsState()

    val buttons = listOf(
        "C","⌫","/","*",
        "7","8","9","-",
        "4","5","6","+",
        "1","2","3","=",
        "0","."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // 🔝 DISPLAY
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = state.value.input,
                fontSize = 28.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Text(
                text = state.value.result,
                fontSize = 48.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        // 🔘 BUTTON GRID
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(buttons) { button ->

                val isOperator = button in listOf("+","-","*","/","=")
                val isClear = button == "C" || button == "⌫"

                Button(
                    onClick = {
                        viewModel.onButtonClick(button)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            isOperator -> MaterialTheme.colorScheme.primary
                            isClear -> MaterialTheme.colorScheme.errorContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    )
                ) {
                    Text(
                        text = button,
                        fontSize = 22.sp,
                        color = when {
                            isOperator -> MaterialTheme.colorScheme.onPrimary
                            isClear -> MaterialTheme.colorScheme.onErrorContainer
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}