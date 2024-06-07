package com.ladescoberta.studentlogs

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ladescoberta.studentlogs.ui.theme.StudentLogsTheme
import kotlinx.serialization.Serializable

private const val TAG = "Investigation"

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentLogsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenA
                ){
                    composable<ScreenA>{
                        Column(
                            modifier=Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(onClick = {
                                navController.navigate(AddRowToBillingInvoice)
                            },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 80.dp)
                                    .padding(10.dp)
                            ){
                                Text(text = stringResource(R.string.home_page_click_to_add_session))
                            }

                            Button(onClick = {
                                navController.navigate(ManageSessions)
                            },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 80.dp)
                                    .padding(10.dp)
                            ){
                                Text(text = stringResource(id = R.string.home_page_click_to_edit_session))
                            }

                            Button(onClick = {
                                navController.navigate(ManageForms)
                            },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 80.dp)
                                    .padding(10.dp)
                            ){
                                Text(text = stringResource(id = R.string.home_page_click_to_manage_reports))
                            }

                            Button(onClick = {
                                navController.navigate(Home(
                                    s1 = "melvyn",
                                    s2 = "drag"
                                ))
                            },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 80.dp)
                                    .padding(10.dp)
                            ){
                                Text(text = "go to test page")
                            }

                            Button(onClick = {
                                navController.navigate(ScreenB(
                                    name="Melvyn",
                                    age=35
                                ))
                            },
                                modifier = Modifier.size(width=200.dp, height=80.dp).padding(10.dp)
                            ){
                                Text(text = "Go to screen B")
                            }

                        }
                    }
                    composable<AddRowToBillingInvoice> {
                        AddRowToBillingInvoiceScreen(
                            onSave = {
                                //val success = navController.popBackStack()
                                //Log.e(TAG, "successful pop? " + success.toString())
                                // TODO WHY DOESNT popBackStack always work?
                                if (!navController.popBackStack()) {
                                    navController.navigate(ScreenA)
                                }
                            }
                        )
                    }
                    composable<ManageSessions> {
                        ManageSessionsScreen(
                            onDone = {
                                //val success = navController.popBackStack()
                                //Log.e(TAG, "successful pop? " + success.toString())
                                // TODO WHY DOESNT popBackStack always work?
                                if (!navController.popBackStack()) {
                                    navController.navigate(ScreenA)
                                }
                            }
                        )
                    }
                    composable<ManageForms> {
                        ManageFormsScreen(
                            onDone = {
                                //val success = navController.popBackStack()
                                //Log.e(TAG, "successful pop? " + success.toString())
                                // TODO WHY DOESNT popBackStack always work?
                                if (!navController.popBackStack()) {
                                    navController.navigate(ScreenA)
                                }
                            }
                        )
                    }
                    composable<ScreenB> {
                        val args = it.toRoute<ScreenB>()
                        Column(
                            modifier=Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(text = "${args.name}, ${args.age} years old")
                        }
                    }
                    composable<Home> {
                        HomeScreen(it.toRoute<Home>())
                    }
                }
            }
        }
    }
}



@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name : String?,
    val age : Int
)