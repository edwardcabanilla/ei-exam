package com.example.myapplication.viewmodels.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.viewmodels.response.Level

@Composable
fun LevelHeadBuilder(level: Level) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 25.dp)
    ) {
        val (image, text, title, description) = createRefs()

        Image(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.level_image),
            contentDescription = "Translated description of what the image contains"
        )
        Text(
            text = "Level ${level.level}",
            color = White,
            modifier = Modifier.constrainAs(text) {
                bottom.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = level.title,
            fontWeight = FontWeight.Bold,
            color = Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = level.description,
            color = Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}