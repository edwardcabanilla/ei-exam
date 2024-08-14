package com.example.myapplication.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.framework.database.entity.ActivityLevelsEnitity

@Dao
interface ActivityLevelsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activityLevelsEnitity: ActivityLevelsEnitity)

    @Update
    fun update(activityLevelsEnitity: ActivityLevelsEnitity): Int

    @Query("SELECT * FROM ACTIVITY_LEVELS WHERE user_id = :user_id")
    fun getLocalActivityLevelsEnitity(user_id: Int): ActivityLevelsEnitity
}