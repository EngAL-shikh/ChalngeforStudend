package com.example.chalngeforcrime.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.chalngeforcrime.Student

@Dao
interface  Dao{
    @Insert
    fun addStudent(student: Student)

    @Query("SELECT * FROM Student")
    fun getStudent():LiveData<List<Student>>

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)


}