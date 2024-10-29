import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr23106sborschikovpr21.DataBaseHelper

@Composable
fun AdminPanel(dbHelper: DataBaseHelper, onBack: () -> Unit) {
    var adminName by remember { mutableStateOf("") }
    var adminPassword by remember { mutableStateOf("") }
    var users by remember { mutableStateOf(listOf<Pair<String, Int>>()) }
    var isAuthenticated by remember { mutableStateOf(false) }

    if (isAuthenticated) {
        // Получаем пользователей
        users = dbHelper.getAllUsers()
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Админ-панель", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Список пользователей:")
            Spacer(modifier = Modifier.height(8.dp))
            for ((index, user) in users.withIndex()) {
                Text("${index + 1}. ${user.first}, Возраст: ${user.second}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onBack() }) {
                Text("Назад")
            }
        }
    } else {
        // Форма для входа
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Вход в админ-панель", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = adminName, onValueChange = { adminName = it }, label = { Text("Логин") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = adminPassword, onValueChange = { adminPassword = it }, label = { Text("Пароль") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (adminName == "root" && adminPassword == "adminpassword") {
                    isAuthenticated = true
                } else {
                    // Здесь можно добавить уведомление об ошибке
                }
            }) {
                Text("Войти")
            }
        }
    }
}