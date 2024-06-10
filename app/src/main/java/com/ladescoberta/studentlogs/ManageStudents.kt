import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ladescoberta.studentlogs.database.MainRepository
import kotlinx.serialization.Serializable

private const val TAG = "ManageStudents"

@Serializable
object ManageStudents

@Composable
fun ManageStudentsScreen(
    onDone: () -> Unit,
    repository: MainRepository
) {
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="manage Students")
    }
}