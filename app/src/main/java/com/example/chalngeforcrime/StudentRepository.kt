package com.example.chalngeforcrime

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.chalngeforcrime.Database.StudentDatabase
import com.example.chalngeforcrime.Database.migration
import java.util.concurrent.Executors

private const val DATABASE_NAME = "student-database"
class StudentRepository private constructor(context: Context){
    private  val database:StudentDatabase= Room.databaseBuilder(
        context.applicationContext,StudentDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration).build()

    private  val studentDao=database.studentDao()
    private  var executor = Executors.newSingleThreadExecutor()
    //private  val filesDir =context.applicationContext.filesDir


     // add new student
    fun addnewstudent(student: Student){
         executor.execute{
             studentDao.addStudent(student)
         }
     }

    //delet user

    fun deleteUser(student: Student){
        executor.execute{
            studentDao.deleteStudent(student)
        }
    }

    // update student
    fun updateStudent(student: Student) {
        executor.execute {
            studentDao.updateStudent(student)
        }
    }

    // select  all student

    fun getStudent(): LiveData<List<Student>> = studentDao.getStudent()

    companion object {
        private var INSTANCE: StudentRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository(context)
            }
        }
        fun get(): StudentRepository {
            return INSTANCE ?:
            throw IllegalStateException("StudentRepository must be initialized")
        }
    }
}