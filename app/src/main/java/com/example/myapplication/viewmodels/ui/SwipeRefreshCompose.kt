package com.example.myapplication.viewmodels.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myapplication.viewmodels.ActivitiesViewModel
import com.example.myapplication.viewmodels.state.ActivitiesState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SwipeRefreshCompose(
    context: Context,
    viewModel: ActivitiesViewModel,
    modifier: Modifier,
    topBar: @Composable () -> Unit
) {
    val state = viewModel.activityLevelsState.collectAsState().value
    val value = when (state) {
        ActivitiesState.Loading -> true
        else -> false
    }

    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(isRefreshing = value),
        onRefresh = { viewModel.getActivityLevels(context) },
    ) {
        topBar.invoke()
    }

}