package com.ladescoberta.studentlogs.database

import android.content.Context

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
        childDao.insert(newChild)
    }

    fun getAllTeachingSessions(): List<TeachingSession>{
        return teachingSessionDao.getAll()
    }

    fun addTeachingSession(teachingSession: TeachingSession) : Unit{
        teachingSessionDao.insert(teachingSession)
    }



}