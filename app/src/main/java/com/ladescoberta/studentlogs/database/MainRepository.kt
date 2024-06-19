package com.ladescoberta.studentlogs.database

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository(context: Context) {
    private val childDao: ChildDao
    private val teachingSessionDao : TeachingSessionDao

    init{
        val database = AppDatabase.getInstance(context)
        childDao = database.childDao()

        teachingSessionDao = database.teachingSessionDao()
    }

    fun getAllChildren() : List<Child>{
        return childDao.getAll()
    }

    fun addChild(newChild : Child) : Unit{
        CoroutineScope(Dispatchers.IO).launch {
            childDao.insert(newChild)
        }
    }

    suspend fun getAllTeachingSessions(): List<TeachingSession>{
        return teachingSessionDao.getAll()
    }

    fun addTeachingSession(teachingSession: TeachingSession) : Unit{
        teachingSessionDao.insert(teachingSession)
    }



}