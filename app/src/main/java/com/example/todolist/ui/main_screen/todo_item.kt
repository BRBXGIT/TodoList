package com.example.todolist.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.todolist.R

@Composable
fun TodoItem() {

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
            .background(Color(0xFFD88EDD))
    ) {
        //Box with date
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFFC27FC6)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "7 DEC",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                fontSize = 15.sp,
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
                    text = "Complete wireframes for Power-x",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                )
                Text(
                    text = "12:00 - 17:30",
                    color = Color.White,
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
                    .border(3.dp, Color(0xFF8B618F), CircleShape)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.not_complited_todo_icon),
                    contentDescription = "Not completed todo icon",
                    tint = Color(0xFF8B618F),
                    modifier = Modifier
                        .size(12.dp)
                )
            }
        }
    }
}