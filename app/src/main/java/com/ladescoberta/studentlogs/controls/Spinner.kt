package com.ladescoberta.studentlogs.controls


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
//import androidx.compose.material3.DropdownMenu
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ladescoberta.studentlogs.R

const val TAG = "Spinner"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner(
    onItemSelected : (String) -> Unit,
    choices: List<String>,
    currentSelection: String
){
    Log.i(TAG, "Recomposing spinner with currentSelection $currentSelection")
    var isExpanded : Boolean by remember {mutableStateOf(false)}
    ExposedDropdownMenuBox(expanded = isExpanded ,
        onExpandedChange ={
            isExpanded = it
        } ) {
        TextField(
            label = { Text(stringResource(id = R.string.session_data_service_type)) },
            value = currentSelection,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false}
        ){
            choices.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick={
                        isExpanded = false
                        Log.i(TAG, "onClick")
                        onItemSelected(it)
                    }
                )
            }
        }
    }

}
