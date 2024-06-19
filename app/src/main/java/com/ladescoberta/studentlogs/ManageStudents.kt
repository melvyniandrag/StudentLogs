import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            Row(modifier = Modifier.fillMaxHeight(0.5F),
                verticalAlignment = Alignment.CenterVertically){
                AddNewStudent(onDone = onDone, repository = repository)
            }
            Row(modifier = Modifier.fillMaxHeight()){
                ListAllStudents(repository)
            }
        }
    }
    else{
        Row(modifier = Modifier.fillMaxSize()){
            Log.e("size", "landscape")
            Column(modifier=Modifier.fillMaxWidth(0.5F)) {
                AddNewStudent(onDone = onDone, repository = repository)
            }
            Column(modifier=Modifier.fillMaxWidth()) {
                ListAllStudents(repository)
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListAllStudents(repository: MainRepository){
    val children by repository.allChildren.collectAsState(initial = emptyList())
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
    )
    {
        stickyHeader {
            Surface(modifier = Modifier.fillMaxWidth()) {
                Text("All my students",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = Bold)
            }
        }
        items(children){ child ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                    Text(
                        text = child.firstName,
                        fontWeight = Bold,
                        fontSize = 22.sp
                    )
                    Text(
                        text = child.lastName,
                        fontSize = 22.sp
                    )
                    Button(onClick={
                        Toast.makeText(context, "Deleting ${child.firstName} ${child.lastName}", Toast.LENGTH_SHORT).show()
                        repository.deleteChild(child)
                    }){
                        Text("delete")
                    }

                }
            }
        }
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Add a Student",
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = Bold)
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
                    focusManager.clearFocus()
                    firstName = ""
                    lastName = ""
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
            Text("Done")
        }

    }
}