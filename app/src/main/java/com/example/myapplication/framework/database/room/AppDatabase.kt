package com.example.myapplication.framework.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.framework.database.dao.ActivityLevelsDao
import com.example.myapplication.framework.database.entity.ActivityLevelsEnitity
import com.example.myapplication.framework.database.room.converters.ActivityLevelsConverter
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        ActivityLevelsEnitity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    ActivityLevelsConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract val activityLevelsDao: ActivityLevelsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val PASSPHRASE = SQLiteDatabase.getBytes("d544b958c201a25dd5389503714c72f8c1c6acf8".toCharArray())
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val builder = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "bogart"
                    )

                    // if additional column etc. are changed
                    //                    builder.addMigrations(
                    //                        DatabaseMigration1To2,
                    //                    )
                    builder.openHelperFactory(SupportFactory(PASSPHRASE))
                    instance = builder.build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
