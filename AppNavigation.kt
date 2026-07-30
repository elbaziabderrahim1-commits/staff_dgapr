import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavigation() {
    val viewModel: EmployeeViewModel = viewModel()
    val employeeList by viewModel.employeeList.collectAsState()

    // تشغيل الشاشة الرئيسية لإضافة الموظفين والمهام
    AddEmployeeScreen()
}
