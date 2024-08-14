package com.example.myapplication.viewmodels.state

import com.example.myapplication.viewmodels.response.ActivityLevels

sealed class ActivitiesState {

    data class Success constructor(val data: ActivityLevels) : ActivitiesState()

    data class Failure constructor(val throwable: Throwable?, val message: String) :
        ActivitiesState()

    object Loading : ActivitiesState()

    object Default : ActivitiesState()
}