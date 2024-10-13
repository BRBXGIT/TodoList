package com.example.todolist.design_system.todo_screen_top_bar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(
    onSetDateClick: (LocalDateTime) -> Unit,
    calendarDialogState: UseCaseState
) {
    CalendarDialog(
        state = calendarDialogState,
        selection = CalendarSelection.Date { calendarDate ->
            onSetDateClick(calendarDate.atStartOfDay())
        }
    )
}