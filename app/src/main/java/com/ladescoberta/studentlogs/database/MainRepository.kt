package com.ladescoberta.studentlogs.database

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainRepository(context: Context) {
    private val childDao: ChildDao
    private val teachingSessionDao : TeachingSessionDao

    init{
        val database = AppDatabase.getInstance(context)
        childDao = database.childDao()

        teachingSessionDao = database.teachingSessionDao()
    }

    val allChildren : Flow<List<Child>> = childDao.getAllSortedByLastName()

    fun addChild(newChild : Child) : Unit{
        CoroutineScope(Dispatchers.IO).launch {
            childDao.insert(newChild)
        }
    }

    fun deleteChild(child: Child) : Unit{
        CoroutineScope(Dispatchers.IO).launch {
            childDao.delete(child)
        }
    }

    suspend fun getAllTeachingSessions(): List<TeachingSession>{
        return teachingSessionDao.getAll()
    }

    fun addTeachingSession(teachingSession: TeachingSession) : Unit{
        teachingSessionDao.insert(teachingSession)
    }



}