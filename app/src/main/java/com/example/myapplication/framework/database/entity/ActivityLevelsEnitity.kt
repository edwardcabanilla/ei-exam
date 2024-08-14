package com.example.myapplication.framework.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.viewmodels.response.ActivityLevels

@Entity(tableName = "ACTIVITY_LEVELS", indices = [ Index(value = ["user_id"], unique = true) ])
data class ActivityLevelsEnitity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id") val user_id: Int,
    @ColumnInfo(name = "activity_levels") val activityLevels: ActivityLevels,
)

fun List<ActivityLevels>.asActivityLevelsEnitity(
    user_id: Int,
): List<ActivityLevelsEnitity> {
    return map { collection ->
        ActivityLevelsEnitity(
            id = 0,
            user_id = user_id,
            activityLevels = collection
        )
    }
}

fun ActivityLevels.asActivityLevelsEnitity(
    user_id: Int,
): ActivityLevelsEnitity {
    return ActivityLevelsEnitity(
        id = 0,
        user_id = user_id,
        activityLevels = this
    )
}

fun ActivityLevelsEnitity.asActivityLevelsEnitity(): ActivityLevels {
    return ActivityLevels(
        levels = activityLevels.levels
    )
}