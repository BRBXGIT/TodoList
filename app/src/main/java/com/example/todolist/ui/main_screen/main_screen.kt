package com.example.todolist.ui.main_screen

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntOffsetAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.AddTodoActivity
import com.example.todolist.R
import com.example.todolist.data.TodoDao
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun MainScreen(dao: TodoDao) {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

    //All todos as state
    val todos = dao.getAllTodos().collectAsState(initial = emptyList())

    //Left menu drawer menu with main screen
    val leftMenuDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = leftMenuDrawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.75f),
                drawerShape = RoundedCornerShape(10.dp),
                drawerContainerColor = Color.White
            ) {
                //Box to fill up space
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color(0xFFE695AB))
                )
                //Home item in left nav menu
                NavigationDrawerItem(
                    label = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.home_icon),
                                contentDescription = "Home icon in left nav menu",
                                tint = Color.Black
                            )
                            Text(
                                text = "Home",
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(0.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White
                    )
                )
                //User item in left nav menu
                NavigationDrawerItem(
                    label = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.user_icon),
                                contentDescription = "User icon in left nav menu",
                                tint = Color.Black
                            )
                            Text(
                                text = "Profile",
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(0.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White
                    )
                )
                //Add to_do item in left nav menu
                NavigationDrawerItem(
                    label = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.add_todo_icon_for_left_menu),
                                contentDescription = "User icon in left nav menu",
                                tint = Color.Black
                            )
                            Text(
                                text = "Add todo",
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(0.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White
                    )
                )
                //Settings item in left nav menu
                NavigationDrawerItem(
                    label = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.settings_icon),
                                contentDescription = "User icon in left nav menu",
                                tint = Color.Black
                            )
                            Text(
                                text = "Settings",
                                fontSize = 20.sp,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(0.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White
                    )
                )
            }
        }
    ) {
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
                                .clickable {
                                    scope.launch {
                                        leftMenuDrawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
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

                dayOfTheWeek = if(dayOfTheWeek == "WEDNESDAY") {
                    "WED"
                } else {
                    dayOfTheWeek.dropLast(3)
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
                            "JUNE" -> month = "JUN"
                            "JULY" -> month = "JUL"
                            "AUGUST" -> month = "AUG"
                            "SEPTEMBER" -> month = "SEP"
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

                //LazyColumn with todos
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
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
                        if(dayOfTodoWithMonth.length < 6) {
                            dayOfTodoWithMonth = "0$dayOfTodoWithMonth"
                        }

                        val pxToMove = with(LocalDensity.current) {
                            30.dp.toPx().roundToInt()
                        }
                        val offset by animateIntOffsetAsState(
                            targetValue = if(todo.isChecked) {
                                IntOffset(pxToMove, 0)
                            } else {
                                IntOffset.Zero
                            },
                            label = "offset",
                        )

                        //Animated colors code block
                        val animatedColorForTodoBackground by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x99D88EDD) else Color(0xFFD88EDD),
                            label = "Animated color for todo background"
                        )

                        val animatedColorForTodoDateBox by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x99C27FC6) else Color(0xFFC27FC6),
                            label = "Animated color for todo background"
                        )

                        val animatedColorForLabels by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x998B618F) else Color(0xFFFFFFFF),
                            label = "Animated color for todo background"
                        )

                        val animatedBackgroundColorForIcon by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x70FFFFFF) else Color(0xFF8B618F),
                            label = "Animated color for todo background"
                        )

                        val animatedBackgroundColorForIconBox by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x998B618F) else Color(0x00),
                            label = "Animated color for todo background"
                        )

                        val animatedBackgroundColorForIconBoxBorder by animateColorAsState(
                            targetValue = if(todo.isChecked) Color(0x00) else Color(0xFF8B618F),
                            label = "Animated color for todo background"
                        )

                        //Box with to_do item
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp, end = 24.dp)
                                .offset {
                                    offset
                                }
                                .padding(bottom = 4.dp)
                        ) {
                            TodoItem(
                                todo.id,
                                todo.title,
                                todo.endTime,
                                todo.date,
                                todo.startTime,
                                todo.isChecked,
                                dao,
                                dayOfTodoWithMonth,
                                animatedColorForTodoBackground,
                                animatedColorForTodoDateBox,
                                animatedColorForLabels,
                                animatedBackgroundColorForIcon,
                                animatedBackgroundColorForIconBox,
                                animatedBackgroundColorForIconBoxBorder,
                            )
                        }
                    }
                }
            }
        }
    }
}
