package com.ladescoberta.studentlogs

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ladescoberta.studentlogs.controls.SessionTypePicker
import com.ladescoberta.studentlogs.controls.Spinner
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG = "Investigation"

@Serializable
object AddRowToBillingInvoice

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddRowToBillingInvoiceScreen(
    onDone: () -> Unit
){
    var firstName by rememberSaveable { mutableStateOf("John")}
    // Too bad this doesnt work it would be cool to have the default value be taken from the string resources
    // so the default name could be localizeable e.g. John in USA but Juan in Mexico.
    //var lastName by rememberSaveable { mutableStateOf(stringResource(R.string.last_name_example))}

    var lastName by rememberSaveable { mutableStateOf("Doe")}


    val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
    val current = LocalDateTime.now().format(formatter)

    var dateOfService by rememberSaveable { mutableStateOf(current)}

    var sessionStartTime by rememberSaveable{ mutableStateOf("12:00AM")}

    var sessionEndTime by rememberSaveable { mutableStateOf("1:00PM")}

    var payRate by rememberSaveable { mutableStateOf("0") }

    var sessionInEIMS by rememberSaveable { mutableStateOf("No" ) }

    var serviceType by rememberSaveable { mutableStateOf("DI" ) }

    var serviceStatus by rememberSaveable { mutableStateOf("Good" ) }

    var sessionLocation by rememberSaveable { mutableStateOf("Library" ) }


    val serviceTypes = listOf("DI", "Speech", "OT")
    val times = listOf("12:00AM", "1:00AM")

    Column(
        modifier= Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.session_data_header),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spinner(
            onItemSelected = {
               serviceType = it
            },
            choices = serviceTypes,
            currentSelection = serviceType
        )
        TextField(
            value = firstName,
            onValueChange = { it ->
                firstName = it
            },
            label = { Text(stringResource(R.string.session_data_student_first_name)) }
        )
        TextField(
            value = lastName,
            onValueChange = { it ->
                lastName = it
            },
            label = { Text(stringResource(R.string.session_data_student_last_name)) }
        )
        TextField(
            value = dateOfService,
            onValueChange = { it ->
                dateOfService = it
            },
            label = { Text(stringResource(R.string.session_data_date_of_service)) }
        )
        TextField(
            value = sessionStartTime,
            onValueChange = { it ->
                sessionStartTime = it
            },
            label = { Text(stringResource(R.string.session_data_start_time)) }
        )
        TextField(
            value = sessionEndTime,
            onValueChange = { it ->
                sessionEndTime = it
            },
            label = { Text(stringResource(R.string.session_data_end_time)) }
        )
        TextField(
            value = payRate,
            onValueChange = { it ->
                payRate = it
            },
            label = { Text(stringResource(R.string.session_data_pay_rate)) }
        )
        TextField(
            value = sessionInEIMS,
            onValueChange = { it ->
                sessionInEIMS = it
            },
            label = { Text(stringResource(R.string.session_data_in_eims)) }
        )
        TextField(
            value = serviceType,
            onValueChange = { it ->
                serviceType = it
            },
            label = { Text(stringResource(R.string.session_data_service_type)) }
        )
        TextField(
            value = serviceStatus,
            onValueChange = { it ->
                serviceStatus = it
            },
            label = { Text(stringResource(R.string.session_data_service_status)) }
        )
        TextField(
            value = sessionLocation,
            onValueChange = { it ->
                sessionLocation = it
            },
            label = { Text(stringResource(R.string.session_data_location)) }
        )
        TextField(
            value = "TODO signature goes here. Signature field should only turn on if everything is filled in. Once you click save, this session's data should be locked and no longer editable.",
            onValueChange = { it ->
                // TODO
                            //sessionLocation = it
            },
            label = { Text(stringResource(R.string.session_data_signature)) }
        )
        Button(
            onClick = {
                Log.e(TAG, "TODO - save session to the database")
                onDone()
            },
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(text = "Save Session")
        }
        Button(
            onClick = {
                Log.e(TAG, "cancelled saving the session")
                onDone()
            },
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .padding(10.dp)
        ){
            Text(text = "Cancel")
        }

    }
}