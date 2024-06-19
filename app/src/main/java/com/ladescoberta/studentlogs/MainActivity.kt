package com.ladescoberta.studentlogs

import ManageStudents
import ManageStudentsScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ladescoberta.studentlogs.database.MainRepository
import com.ladescoberta.studentlogs.ui.theme.StudentLogsTheme

private const val TAG = "Investigation"

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = MainRepository(this)
        enableEdgeToEdge()
        setContent {
            StudentLogsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home
                ){
                    composable<Home>{
                        HomeScreen(navController)
                    }
                    composable<AddRowToBillingInvoice> {
                        AddRowToBillingInvoiceScreen(
                            onDone = {
                                if (!navController.popBackStack()) {
                                    navController.navigate(Home)
                                }
                            }
                        )
                    }
                    composable<ManageSessions> {
                        ManageSessionsScreen(
                            onDone = {
                                if (!navController.popBackStack()) {
                                    navController.navigate(Home)
                                }
                            },
                            repository = repository
                        )
                    }
                    composable<ManageForms> {
                        ManageFormsScreen(
                            onDone = {
                                if (!navController.popBackStack()) {
                                    navController.navigate(Home)
                                }
                            },
                            repository = repository
                        )
                    }
                    composable<ManageStudents> {
                        ManageStudentsScreen(
                            onDone = {
                                if (!navController.popBackStack()) {
                                    navController.navigate(Home)
                                }
                            },
                            repository = repository
                        )
                    }
                }
            }
        }
    }
}