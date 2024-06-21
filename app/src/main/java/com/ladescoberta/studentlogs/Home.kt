package com.ladescoberta.studentlogs

import ManageStudents
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun HomeScreen(
    navController: NavController
){
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text=stringResource(R.string.app_name),
            fontSize = 35.sp,
            fontWeight = Bold
        )
        Button(onClick = {
            navController.navigate(ManageStudents)
        },
            modifier = Modifier
                .size(width = 250.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(
                text = stringResource(R.string.home_page_manage_students),
                fontSize = 18.sp)
        }

        Button(onClick = {
            navController.navigate(AddRowToBillingInvoice)
        },
            modifier = Modifier
                .size(width = 250.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(text = stringResource(R.string.home_page_click_to_add_session),
            fontSize = 18.sp)
        }

        Button(onClick = {
            navController.navigate(ManageSessions)
        },
            modifier = Modifier
                .size(width = 250.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(text = stringResource(id = R.string.home_page_click_to_edit_session),
                fontSize = 18.sp)
        }

        Button(onClick = {
            navController.navigate(ManageForms)
        },
            modifier = Modifier
                .size(width = 250.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(text = stringResource(id = R.string.home_page_click_to_manage_reports),
                fontSize = 18.sp)
        }
    }
}