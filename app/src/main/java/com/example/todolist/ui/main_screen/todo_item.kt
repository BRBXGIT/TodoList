package com.example.todolist.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.R
import com.example.todolist.data.TodoDao

@Composable
fun TodoItem(
    id: Int,
    title: String,
    endTime: String,
    date: String,
    startTime: String,
    isChecked: Boolean,
    dao: TodoDao,
    dayOfTodoWithMonth: String,
    animatedColorForTodoBackground: Color,
    animatedColorForTodoDateBox: Color,
    animatedColorForLabels: Color,
    animatedBackgroundColorForIcon: Color,
    animatedBackgroundColorForIconBox: Color,
    animatedBackgroundColorForIconBoxBorder: Color,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

    //Main row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(10.dp))
            .drawBehind {
                drawRect(animatedColorForTodoBackground)
            }
    ) {
        //Box with date
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(animatedColorForTodoDateBox),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayOfTodoWithMonth,
                color = animatedColorForLabels,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                fontSize = 12.sp,
                modifier = Modifier
                    .rotate(-90f)
                    .alpha(0.7f)
            )
        }
        //Row with to_do information
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = animatedColorForLabels,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                )
                Text(
                    text = "$startTime - $endTime",
                    color = animatedColorForLabels,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(3.dp, animatedBackgroundColorForIconBoxBorder, CircleShape)
                    .clip(CircleShape)
                    .background(animatedBackgroundColorForIconBox)
                    .noRippleClickable {
                        mainScreenViewModel.updateExistingTodo(
                            id,
                            title,
                            startTime,
                            endTime,
                            date,
                            isChecked,
                            dao
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                if(isChecked) {
                    Icon(
                        painter = painterResource(id = R.drawable.completed_todo_icon),
                        contentDescription = "Not completed todo icon",
                        tint = animatedBackgroundColorForIcon,
                        modifier = Modifier
                            .size(28.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.not_complited_todo_icon),
                        contentDescription = "Not completed todo icon",
                        tint = animatedBackgroundColorForIcon,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}