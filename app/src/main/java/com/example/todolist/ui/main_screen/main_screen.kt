package com.example.todolist.ui.main_screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.AddTodoActivity
import com.example.todolist.R
import com.example.todolist.data.TodoDao
import com.example.todolist.data.TodoDb
import com.example.todolist.ui.add_todo_screen.AddTodo
import java.time.LocalDate

@Composable
fun MainScreen(dao: TodoDao) {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

    val todos = dao.getAllTodos().collectAsState(initial = emptyList())

    //Main box with gradient
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
    ) {
        Column {
            //Add button and menu box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.12f)
                    .padding(top = 32.dp, start = 24.dp, end = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = "menu icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                            .alpha(0.5f)
                    )

                    //Add to_do button
                    val context = LocalContext.current
                    ElevatedButton(
                        onClick = {
                            context.startActivity(Intent(context, AddTodoActivity::class.java))
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .alpha(0.9f),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(0x80000000),
                            containerColor = Color(0xFF98FB98)
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_todo_icon),
                            contentDescription = "Add todo icon",
                            Modifier.size(30.dp)
                        )
                    }
                }
            }

            var dayOfTheWeek = LocalDate.now().dayOfWeek.toString()

            if(dayOfTheWeek == "WEDNESDAY") {
                dayOfTheWeek = "WED"
            } else {
                dayOfTheWeek = dayOfTheWeek.dropLast(3)
            }

            //Day of the week and date box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-45).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dayOfTheWeek,
                            fontSize = 100.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier
                                .alpha(0.2f)
                        )
                        Text(
                            text = "DAY",
                            fontSize = 140.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier
                                .alpha(0.2f)
                        )
                    }
                    //Date box

                    val dayOfMonth = LocalDate.now().dayOfMonth.toString()
                    var month = LocalDate.now().month.toString()

                    when(month) {
                        "JANUARY" -> month = "JAN"
                        "FEBRUARY" -> month = "FEB"
                        "MARCH" -> month = "MAR"
                        "APRIL" -> month = "APR"
                        "MAY" -> month = "MAY"
                        "JUNE" -> month = "JUNE"
                        "JULY" -> month = "JULY"
                        "AUGUST" -> month = "AUG"
                        "SEPTEMBER" -> month = "SEPT"
                        "OCTOBER" -> month = "OCT"
                        "NOVEMBER" -> month = "NOV"
                        "DECEMBER" -> month = "DEC"
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.7f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy((-90).dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$dayOfMonth\n",
                                color = Color.White,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 90.sp,
                            )
                            Text(
                                text = month,
                                color = Color.Black,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 70.sp,
                                modifier = Modifier
                                    .alpha(0.8f)
                            )
                        }
                    }
                }
            }
            //Box with todos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(todos.value) { todo ->

                    val dayOfTodo = LocalDate.parse(todo.date).dayOfMonth.toString()
                    var monthOfTodo = LocalDate.parse(todo.date).month.toString()
                    when(monthOfTodo) {
                        "JANUARY" -> monthOfTodo = "JAN"
                        "FEBRUARY" -> monthOfTodo = "FEB"
                        "MARCH" -> monthOfTodo = "MAR"
                        "APRIL" -> monthOfTodo = "APR"
                        "MAY" -> monthOfTodo = "MAY"
                        "JUNE" -> monthOfTodo = "JUNE"
                        "JULY" -> monthOfTodo = "JULY"
                        "AUGUST" -> monthOfTodo = "AUG"
                        "SEPTEMBER" -> monthOfTodo = "SEPT"
                        "OCTOBER" -> monthOfTodo = "OCT"
                        "NOVEMBER" -> monthOfTodo = "NOV"
                        "DECEMBER" -> monthOfTodo = "DEC"
                    }
                    var dayOfTodoWithMonth = "$dayOfTodo $monthOfTodo"

                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(start = 24.dp, end = 24.dp)
                   ) {
                       TodoItem(
                           todo.title,
                           todo.endTime,
                           dayOfTodoWithMonth,
                           todo.startTime
                       )
                   }
                }
            }
        }
    }
}