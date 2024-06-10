package com.ladescoberta.studentlogs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlin.concurrent.Volatile

@Database(entities = [Child::class, TeachingSession::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun childDao() : ChildDao

    abstract fun teachingSessionDao() : TeachingSessionDao

    companion object{
        @Volatile
        private var _instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                var instance = _instance
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                    _instance = instance
                }
                return instance
            }
        }
    }

}
