import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddEmployeeScreen() {
    var name by remember { mutableStateOf("") }
    var adminId by remember { mutableStateOf("") }
    var workCenter by remember { mutableStateOf("") }

    val daysOfWeek = listOf("الإثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت", "الأحد")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "إضافة موظف جديد", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = adminId,
            onValueChange = { adminId = it },
            label = { Text("الرقم الإداري") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("اسم الموظف") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = workCenter,
            onValueChange = { workCenter = it },
            label = { Text("مركز العمل") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "توزيع المهام الأسبوعية", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        daysOfWeek.forEach { day ->
            var task by remember { mutableStateOf("") }
            var isRestDay by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = day, style = MaterialTheme.typography.titleMedium)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("يوم راحة؟")
                            Checkbox(
                                checked = isRestDay,
                                onCheckedChange = { isRestDay = it }
                            )
                        }
                    }

                    if (!isRestDay) {
                        OutlinedTextField(
                            value = task,
                            onValueChange = { task = it },
                            label = { Text("المهمة المخصصة") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // حفظ البيانات
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("حفظ بيانات الموظف")
        }
    }
}
