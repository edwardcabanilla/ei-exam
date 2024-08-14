package com.example.myapplication.viewmodels.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BlueViolet

@Composable
internal fun ActivitiesStateFailedBuilder(onRetryLoading: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp, end = 15.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Something went wrong while loading. Please try again.",
                style = TextStyle(
                    color = Black,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { onRetryLoading?.invoke() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueViolet,
                    contentColor = White
                ),
                elevation = null,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 5.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null,
                        tint = White
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center,
                        text = "Try Again",
                        style = TextStyle(
                            color = White,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}