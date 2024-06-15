package com.ladescoberta.studentlogs

import ManageStudents
import ManageStudentsScreen
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.ladescoberta.studentlogs.database.MainRepository
import com.ladescoberta.studentlogs.ui.theme.StudentLogsTheme
import kotlinx.serialization.Serializable

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
                    startDestination = ScreenA
                ){
                    composable<ScreenA>{
                        Column(
                            modifier=Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(onClick = {
                                navController.navigate(ManageStudents)
                            },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 80.dp)
                                    .padding(10.dp)
                            ){
                                Text(text = stringResource(R.string.home_page_manage_students))
                            }

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
                        }
                    }
                    composable<AddRowToBillingInvoice> {
                        AddRowToBillingInvoiceScreen(
                            onSave = {
                                //val success = navController.popBackStack()
                                //Log.e(TAG, "successful pop? " + success.toString())
                                // TODO WHY DOESNT popBackStack always work?
                                //if (!navController.popBackStack()) {
                                //    navController.navigate(ScreenA)
                                //}
                                navController.navigateUp()
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
                    composable<ManageStudents> {
                        ManageStudentsScreen(
                            onDone = {
                                //val success = navController.popBackStack()
                                //Log.e(TAG, "successful pop? " + success.toString())
                                // TODO WHY DOESNT popBackStack always work?
                                if (!navController.popBackStack()) {
                                    navController.navigate(ScreenA)
                                }
                            },
                            repository = repository
                        )
                    }
                    composable<Home> {
                        HomeScreen(it.toRoute<Home>())
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 101){
            if(grantResults.size > 1){ // code example says to check if greater than 0, but it seems to make more sense that there are at least two results since we are requesting two
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Permissions not granted....", Toast.LENGTH_SHORT).show()
                    finish()
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