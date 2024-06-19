package com.ladescoberta.studentlogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ladescoberta.studentlogs.database.MainRepository
import kotlinx.serialization.Serializable

private const val TAG = "ManageSessions"

@Serializable
object ManageSessions

@Composable
fun ManageSessionsScreen(
    onDone: () -> Unit,
    repository: MainRepository
) {
    Text(text="manage sessions")

}