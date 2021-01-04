package com.example.chalngeforcrime

import android.app.Application

class StudentApplication:Application (){
    override fun onCreate() {
        super.onCreate()
        StudentRepository.initialize(this)
    }


}