package com.example.chalngeforcrime

import androidx.lifecycle.ViewModel
import java.util.*

class StudentViewModel : ViewModel() {
    val Students = mutableListOf<Student>()

    fun addnewstudent(student: Student){
        Students.add(student)
    }

    fun deleted(item:Int){

        Students.removeAt(item)


    }
    init {
        for (i in 0 until 5) {
            val student =
                Student()
            student.name = "student #$i"
            student.number = i
            student.passed = i % 2 == 0
            Students += student
        }
    }




}