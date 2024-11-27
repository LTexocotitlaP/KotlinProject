package org.example.project

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        FootballGraphVisualizer()
    }
}

@Composable
@Preview
fun FootballGraphVisualizer() {
    val numberOfTeams = 22
    val radius = 300f
    val centerX = 400f
    val centerY = 400f
    val angleIncrement = 2 * PI / numberOfTeams

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Calculate team positions
        val positions = List(numberOfTeams) { index ->
            val angle = angleIncrement * index
            Offset(
                x = centerX + radius * cos(angle).toFloat(),
                y = centerY + radius * sin(angle).toFloat()
            )
        }

        // Draw edges
        for (i in positions.indices) {
            for (j in i + 1 until positions.size) {
                drawLine(
                    color = Color.Gray,
                    start = positions[i],
                    end = positions[j],
                    strokeWidth = 1f
                )
            }
        }

        // Draw nodes
        positions.forEach { position ->
            drawCircle(color = Color.Blue, center = position, radius = 10f)
        }
    }
}
