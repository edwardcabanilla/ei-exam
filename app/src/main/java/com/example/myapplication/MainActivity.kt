package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.framework.database.room.AppDatabase
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.ActivitiesViewModel
import com.example.myapplication.viewmodels.state.ActivitiesState
import com.example.myapplication.viewmodels.ui.DashboardBuilder

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ActivitiesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.db = AppDatabase.getInstance(context = this)
        viewModel.getActivityLevels(this)
        lifecycleScope.apply {
            launchWhenCreated { collectActivityLevelsLocal() }
        }
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    DashboardBuilder(
                        context = this,
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    private suspend fun collectActivityLevelsLocal() {
        viewModel.activityLevelsState.collect { activityLevelsState ->
            when (activityLevelsState) {
                is ActivitiesState.Success -> {
//                    viewModel.getActivityLevelsLocal()
                }
                else -> {}
            }
        }
    }
}