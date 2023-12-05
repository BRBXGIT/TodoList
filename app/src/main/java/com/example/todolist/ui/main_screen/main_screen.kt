package com.example.todolist.ui.main_screen

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
fun MainScreen() {

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
                    ElevatedButton(
                        onClick = { /*TODO*/ },
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
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add todo icon"
                        )
                    }
                }
            }
            //Date box
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
                            text = "SUN",
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.7f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(-80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "27\n",
                                color = Color.White,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 90.sp,
                                modifier = Modifier
                                    .padding(bottom = 32.dp)
                            )
                            Text(
                                text = "FEB",
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
        }
    }
}