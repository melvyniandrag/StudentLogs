package com.ladescoberta.studentlogs.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TeachingSession (
    // unique id for the session.
    @PrimaryKey val sessionID: Int,

    // the child this session is for. Allow null if this session doesnt pertain to a child
    @ColumnInfo(name="child")  val child: Child?,

    // when did the session start?
    // NOTE: We can extract the date for the session from the sessionStart Date.
    // the Date class captured date and time
    @ColumnInfo(name="session_start") val sessionStart: Date,

    // when did the session end?
    @ColumnInfo(name="session_end") val sessionEnd: Date,

    // hourly rate for the session?
    @ColumnInfo(name="pay_rate") val payRate: Double,

    // was it logged to EIMS ( or other? )
    @ColumnInfo(name="session_logged") val sessionLogged: Boolean?,

    // should be a key from the serviceStatusMap
    @ColumnInfo(name="service_status") val serviceStatus: String,

    // should be a key from the serviceTypeCodeMap
    @ColumnInfo(name="service_type") val serviceType: String,

    // should be a key from the serviceLocationMap
    @ColumnInfo(name="service_location") val serviceLocation: String,

    // should be the location where the parent signature file can be retrieved.
    @ColumnInfo(name="parent_signature_file") val parentSignatureFile: String?
)
