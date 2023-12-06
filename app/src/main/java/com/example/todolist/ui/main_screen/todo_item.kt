package com.example.todolist.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R

@Composable
fun TodoItem() {

    val fontFamily = FontFamily(
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_light, FontWeight.Light),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .clip(RoundedCornerShape(5.dp))
            .shadow(5.dp)
            .background(Color(0xFFD88EDD))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.12f)
                .background(Color(0xFFC27FC6)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "27 SEPT",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .rotate(-90f)
                    .background(Color.Red),
            )
        }
    }
}