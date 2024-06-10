package com.ladescoberta.studentlogs.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// TODO see the comments in the TeachingSessionDao class
// about making these methods suspend/LiveData/Flow
@Dao interface ChildDao {
    @Query("SELECT * FROM Child")
    fun getAll(): List<Child>

    @Query("SELECT * FROM Child WHERE first_name = :first AND last_name = :last LIMIT 1")
    fun getByName(first: String, last: String): Child

    @Update
    fun update(child: Child)

    @Insert
    fun insert(child: Child)

    @Delete
    fun delete(child: Child)

}