package com.example.myapplication.viewmodels.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.viewmodels.ActivitiesViewModel
import com.example.myapplication.viewmodels.state.ActivitiesState

@Composable
fun DashboardBuilder(context: Context, viewModel: ActivitiesViewModel, modifier: Modifier) {
    val openedDocument = viewModel.openedDocument.collectAsState().value
    val bitmap = viewModel.bitmap.collectAsState().value
    val activityLevelsState = viewModel.activityLevelsState.collectAsState().value
    val activityLevelsLocal = viewModel.activityLevelsLocal.collectAsState().value
    SwipeRefreshCompose(context, viewModel, modifier) {
        var tabIndex by remember { mutableIntStateOf(0) }
        val tabs = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

        val totalActivities = when (activityLevelsState) {
            is ActivitiesState.Success -> {
                activityLevelsState.data.levels.sumOf { level ->
                    level.activities.size
                }.toFloat()
            }
            else -> 0f
        }

        Column {
            HeaderBuilder(
                openedDocument.size.toFloat() / totalActivities
            )
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Column {
                                Image(
                                    modifier = Modifier
                                        .size(40.dp, 40.dp),
                                    painter = painterResource(
                                        id = when {
                                            tabIndex == index -> R.drawable.tab_selected
                                            else -> R.drawable.tab_unselected
                                        }
                                    ),
                                    contentDescription = "Translated description of what the image contains"
                                )
                                Text(
                                    text = title,
                                    fontSize = 12.sp
                                )
                            }
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> when (activityLevelsState) {
                    ActivitiesState.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(64.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }

                    }
                    is ActivitiesState.Success -> {
                        LevelColumnBuilder(
                            context = context ,
                            data = activityLevelsState.data,
                            viewModel = viewModel,
                            openedDocument = openedDocument,
                            bitmap = bitmap
                        )
                    }
                    else -> {
                        ActivitiesStateFailedBuilder {
                            viewModel.getActivityLevels(context = context)
                        }
                    }
                }
                1 -> when (activityLevelsLocal) {
                    ActivitiesState.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(64.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }

                    }
                    is ActivitiesState.Success -> {
                        LevelColumnBuilder(
                            context = context ,
                            data = activityLevelsLocal.data,
                            viewModel = viewModel,
                            openedDocument = openedDocument,
                            bitmap = bitmap
                        )
                    }
                    else -> {
                        ActivitiesStateFailedBuilder {
                            viewModel.getActivityLevelsLocal()
                        }
                    }
                }
                2 -> {}
            }

        }
    }

}