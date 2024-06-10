package com.ladescoberta.studentlogs.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


// TODO
// Consider whether or not to make the various methods here "suspend" methods
// OR we could have them return LiveData<TeachingSession>
// OR we could have them return Flow<TeachingSession>
// OR we could just leave them synchronous as they are.
// Undoubtedly there are other options as well.
@Dao
interface TeachingSessionDao {
    @Query("SELECT * FROM TeachingSession")
    fun getAll(): List<TeachingSession>

    @Query("SELECT * FROM TeachingSession WHERE sessionID = :sessionID LIMIT 1")
    fun getByID(sessionID: Int): TeachingSession

    @Insert
    fun insert(teachingSession: TeachingSession)

    @Update
    fun update(teachingSession: TeachingSession)

    @Delete
    fun delete(teachingSession: TeachingSession)
}