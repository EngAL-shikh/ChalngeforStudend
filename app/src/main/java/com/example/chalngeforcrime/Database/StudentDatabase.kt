package com.example.chalngeforcrime.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chalngeforcrime.Student

@Database(entities = [Student::class],version = 1)

abstract class  StudentDatabase:RoomDatabase(){
    abstract  fun studentDao():Dao




}

val migration=object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Student ADD COLUMN suspect TEXT NOT NULL DEFAULT ''")
    }
}