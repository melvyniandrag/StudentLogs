package com.ladescoberta.studentlogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

private const val TAG = "ManageSessions"

@Serializable
object ManageSessions

@Composable
fun ManageSessionsScreen(
    onDone: () -> Unit
) {
    Text(text="manage sessions")

}