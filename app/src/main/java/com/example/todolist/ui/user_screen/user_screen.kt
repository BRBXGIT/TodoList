package com.example.todolist.ui.user_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R

@Composable
fun UserScreen(

) {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

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
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.go_back_icon),
                    tint = Color(0x90FFFFFF),
                    contentDescription = "Go back to MainActivity icon",
                    modifier = Modifier
                        .size(50.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Username",
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .alpha(0.8f)
                        .align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0x607F5885)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyRow {
                            items(1000) { item ->
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 8.dp)
                                ) {
                                    Text(
                                        text = "$item TODOS",
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "24 DEC",
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Thin,
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f)
                    ) {
                        Divider(
                            thickness = 1.dp,
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Today 14 todos",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}