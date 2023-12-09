package com.example.todolist.ui.add_todo_screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.data.TodoDao
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodo(
    addTodoViewModel: AddTodoViewModel = viewModel(),
    dao: TodoDao
) {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

    //Calendar states
    val calendarState = rememberSheetState()
    var chosenDate by remember { mutableStateOf("") }

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            chosenDate = date.toString()
        }
    )

    //Clock states
    val startClockState = rememberSheetState()
    var chosenStartTime by remember { mutableStateOf("") }

    ClockDialog(
        state = startClockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            chosenStartTime = "$hours:$minutes"
        },
        config = ClockConfig(
            is24HourFormat = true
        )
    )

    val endClockState = rememberSheetState()
    var chosenEndTime by remember { mutableStateOf("") }

    ClockDialog(
        state = endClockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            chosenEndTime = "$hours:$minutes"
        },
        config = ClockConfig(
            is24HourFormat = true
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE695AB),
                        Color(0xFF7F5885)
                    )
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0x607F5885))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Configure your todo",
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .alpha(0.8f)
                    .align(Alignment.TopCenter)
            )

            var todoTitle by remember { mutableStateOf("") }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = todoTitle,
                    onValueChange = { todoTitle = it },
                    label = { Text(
                        text = "Title",
                        fontFamily = fontFamily,
                        color = Color.White
                    ) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        disabledLabelColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                //Add date to_do
                Button(
                    onClick = {
                        calendarState.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0x80000000),
                        containerColor = Color(0xFF98FB98)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Choose date",
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            modifier = Modifier
                                .alpha(0.8f)
                        )
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar icon",
                        )
                    }
                }

                //Add start time to_do
                Button(
                    onClick = {
                        startClockState.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0x80000000),
                        containerColor = Color(0xFF98FB98)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Choose start time",
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            modifier = Modifier
                                .alpha(0.8f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_choose_time),
                            contentDescription = "Start time icon"
                        )
                    }
                }

                //Add end time to_do
                Button(
                    onClick = {
                        endClockState.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0x80000000),
                        containerColor = Color(0xFF98FB98)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Choose end time",
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            modifier = Modifier
                                .alpha(0.8f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_choose_time),
                            contentDescription = "End time icon"
                        )
                    }
                }
            }

            //Create to_do button
            val context = LocalContext.current
            Button(
                onClick = {
                    addTodoViewModel.upsertNewTodo(
                        dao = dao,
                        startTime = chosenStartTime,
                        endTime = chosenEndTime,
                        date = chosenDate,
                        title = todoTitle
                    )
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0x80000000),
                    containerColor = Color(0xFF98FB98)
                ),
            ) {
                Text(
                    text = "Create todo",
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    modifier = Modifier
                        .alpha(0.8f)
                )
            }
        }
    }
}