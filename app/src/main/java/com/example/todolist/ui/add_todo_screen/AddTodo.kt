package com.example.todolist.ui.add_todo_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.R
import com.example.todolist.data.TodoDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodo() {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
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
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0x607F5885))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            var todoTitle by remember { mutableStateOf("") }

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

            Button(
                onClick = {

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