package com.example.myapplication.framework.database.room.converters

import androidx.room.TypeConverter
import com.example.myapplication.viewmodels.response.ActivityLevels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ActivityLevelsConverter {

    @TypeConverter
    fun toActivityLevels(string: String?): ActivityLevels? {
        return Gson().fromJson(string, ActivityLevels::class.java)
    }

    @TypeConverter
    fun toActivityLevelsString(attachedFiles: ActivityLevels?): String? {
        return Gson().toJson(attachedFiles)
    }
}
