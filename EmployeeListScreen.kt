import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmployeeListScreen(employeeList: List<EmployeeEntity>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "لائحة الموظفين والمهام الأسبوعية",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // عرض قائمة الموظفين
        LazyColumn {
            items(employeeList) { employee ->
                EmployeeCard(employee = employee)
            }
        }
    }
}

@Composable
fun EmployeeCard(employee: EmployeeEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "الاسم: ${employee.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "الرقم الإداري: ${employee.adminId}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "مركز العمل: ${employee.workCenter}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
