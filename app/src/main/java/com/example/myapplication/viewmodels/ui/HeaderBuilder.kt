package com.example.myapplication.viewmodels.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.ui.theme.BlueViolet

@Composable
fun HeaderBuilder(
    currentProgress: Float,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        // Create references for the composables to constrain
        val (headerIcon, fireIcon, image, progress) = createRefs()
        Image(
            modifier = Modifier
                .size(40.dp, 40.dp)
                .constrainAs(headerIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            painter = painterResource(id = R.drawable.header_icon),
            contentDescription = "Translated description of what the image contains"
        )
        Column(
            modifier = Modifier
                .constrainAs(progress) {
                    bottom.linkTo(parent.bottom, 10.dp)
                    start.linkTo(headerIcon.end, 10.dp)
                }
        ) {
            Text(
                text = "Taming Temper",
                color = Black,
                fontWeight = FontWeight.Bold
            )
            Row {
                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier
                        .width(80.dp)
                        .padding(5.dp),
                )
                Text(
                    text = "${(currentProgress * 100).toInt()}%",
                    fontSize = 10.sp,
                    color = BlueViolet
                )
            }
        }
        Row(
            modifier = Modifier
                .constrainAs(fireIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(image.start, 10.dp)
                }
        ) {
            Image(
                modifier = Modifier
                    .size(30.dp, 30.dp),
                painter = painterResource(id = R.drawable.fire_icon),
                contentDescription = "Translated description of what the image contains"
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                text = "0",
                fontSize = 16.sp,
                color = BlueViolet
            )
        }
        Image(
            modifier = Modifier
                .size(50.dp, 50.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.image_icon),
            contentDescription = "Translated description of what the image contains"
        )
    }
}