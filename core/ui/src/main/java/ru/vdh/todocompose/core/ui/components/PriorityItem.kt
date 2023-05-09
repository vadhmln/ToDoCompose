package ru.vdh.todocompose.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.vdh.todocompose.core.ui.theme.HighPriorityColor
import ru.vdh.todocompose.core.ui.theme.LARGE_PADDING
import ru.vdh.todocompose.core.ui.theme.LowPriorityColor
import ru.vdh.todocompose.core.ui.theme.MediumPriorityColor
import ru.vdh.todocompose.core.ui.theme.NonePriorityColor
import ru.vdh.todocompose.core.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(priority: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)){
            drawCircle(color = parsePriority(priority) )
        }
        Text(
            modifier = Modifier
                .padding(start = LARGE_PADDING),
            text = priority,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

fun parsePriority(priority: String) =
    when (priority) {
        "HIGH" -> {
            HighPriorityColor
        }

        "MEDIUM" -> {
            MediumPriorityColor
        }

        "LOW" -> {
            LowPriorityColor
        }

        else -> NonePriorityColor
    }

@Composable
@Preview
fun PriorityItemPreview(){
    PriorityItem(priority = "HIGH")
}