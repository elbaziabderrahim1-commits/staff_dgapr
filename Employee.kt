import android.content.Context
import androidx.room.*

// جدول الموظفين
@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val adminId: String,
    val name: String,
    val workCenter: String
)

// جدول المهام اليومية
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val adminId: String,       // ربط المهمة بالموظف عبر رقمه الإداري
    val dayOfWeek: String,     // الإثنين، الثلاثاء...
    val taskDescription: String,
    val isRestDay: Boolean
)

// الواجهة ديال التعامل مع قاعدة البيانات (DAO)
@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)
}

// بناء قاعدة البيانات
@Database(entities = [EmployeeEntity::class, TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "staff_dgapr_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
