import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val dao = database.employeeDao()

    private val _employeeList = MutableStateFlow<List<EmployeeEntity>>(emptyList())
    val employeeList: StateFlow<List<EmployeeEntity>> = _employeeList

    init {
        loadEmployees()
    }

    // دالة لحفظ موظف جديد والمهام ديالو
    fun saveEmployee(adminId: String, name: String, workCenter: String, tasks: List<TaskEntity>) {
        viewModelScope.launch {
            val employee = EmployeeEntity(adminId, name, workCenter)
            dao.insertEmployee(employee)
            dao.insertTasks(tasks)
            loadEmployees() // تحديث القائمة بعد الحفظ
        }
    }

    // دالة لتحميل لائحة الموظفين
    private fun loadEmployees() {
        viewModelScope.launch {
            _employeeList.value = dao.getAllEmployees()
        }
    }
}
