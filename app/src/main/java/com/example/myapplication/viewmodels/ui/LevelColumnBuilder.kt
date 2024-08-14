package com.example.myapplication.viewmodels.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.BlueViolet
import com.example.myapplication.ui.theme.PurpleGrey80
import com.example.myapplication.viewmodels.ActivitiesViewModel
import com.example.myapplication.viewmodels.response.ActivityLevels

@Composable
fun LevelColumnBuilder(
    context: Context,
    data: ActivityLevels,
    viewModel: ActivitiesViewModel,
    openedDocument: List<String>,
    bitmap: HashMap<String, Bitmap>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data.levels.forEach { level ->
            LevelHeadBuilder(level)
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 25.dp)
            ) {
                level.activities.forEachIndexed { index, activity ->
                    when {
                        ( index % 2 ) == 0 -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                ImageBuilder(
                                    context = context,
                                    activity = activity,
                                    openedDocument = openedDocument,
                                    viewModel = viewModel,
                                    bitmapHash = bitmap,
                                )
                                if ((index + 1) <= level.activities.lastIndex) {
                                    ImageBuilder(
                                        context = context,
                                        activity = level.activities[index + 1],
                                        openedDocument = openedDocument,
                                        viewModel = viewModel,
                                        bitmapHash = bitmap,
                                    )
                                }
                            }
                        }
                        else -> { }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.padding(bottom = 15.dp),
            thickness = 1.dp,
            color = PurpleGrey80
        )
        Image(
            modifier = Modifier
                .size(20.dp, 20.dp),
            painter = painterResource(
                id = R.drawable.flag
            ),
            contentDescription = "Translated description of what the image contains"
        )
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = "Journey",
            color = BlueViolet
        )
    }
}