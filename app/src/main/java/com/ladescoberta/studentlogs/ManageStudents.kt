import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.ladescoberta.studentlogs.R
import com.ladescoberta.studentlogs.database.Child
import com.ladescoberta.studentlogs.database.MainRepository
import kotlinx.serialization.Serializable

private const val TAG = "ManageStudents"

@Serializable
object ManageStudents

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ManageStudentsScreen(
    onDone: () -> Unit,
    repository: MainRepository
) {
    Log.i("size", "ManageStudentsScreen")
    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Log.e("size", "portrait")
            Row(modifier = Modifier.fillMaxHeight(0.5F)){
                AddNewStudent(onDone = onDone, repository = repository)
            }
            Row(modifier = Modifier.fillMaxHeight(0.5F)){
                ListAllStudents()
            }
        }
    }
    else{
        Row(modifier = Modifier.fillMaxSize()){
            Log.e("size", "landscape")
            Column(modifier=Modifier.fillMaxWidth(0.5F)) {
                AddNewStudent(onDone = onDone, repository = repository)
            }
            Column(modifier=Modifier.fillMaxWidth(0.5F)) {
                ListAllStudents()
            }
        }
    }
}
@Composable
fun ListAllStudents(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "hello")
        Text(text = "hello")
        Text(text = "hello")
    }


}
@Composable
fun AddNewStudent(
    onDone: () -> Unit,
    repository: MainRepository
) {
    var firstName: String by remember { mutableStateOf("") }
    var lastName: String by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add a Student",
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            label = { Text(stringResource(R.string.session_data_student_first_name)) },
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            label = { Text(stringResource(R.string.session_data_student_last_name)) },
            modifier = Modifier.padding(10.dp)
        )
        Button(
            onClick = {
                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    val newChild = Child(firstName = firstName, lastName = lastName)
                    repository.addChild(newChild)
                    Toast.makeText(context, "Child $firstName $lastName saved", Toast.LENGTH_LONG)
                        .show()
                    onDone()
                } else {
                    Toast.makeText(
                        context,
                        "Make sure first and last name are not empty",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .padding(10.dp)
        ) {
            Text("Save")
        }
        Button(
            onClick = {
                onDone()
            },
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .padding(10.dp)
        ) {
            Text("Cancel")
        }

    }
}