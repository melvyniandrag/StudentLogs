package com.ladescoberta.studentlogs.controls


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionTypePicker(){
    var isExpanded : Boolean by remember {mutableStateOf(false)}
    var sessionType: String by remember {mutableStateOf("DI")}
    var sessionTypes = listOf("DI", "speech", "other")
    ExposedDropdownMenuBox(expanded = isExpanded ,
        onExpandedChange ={
            isExpanded = it
        } ) {
        TextField(
            value = sessionType,
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
            sessionTypes.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick={
                        sessionType = it
                        isExpanded = false
                    }
                )
            }
        }
    }

}
