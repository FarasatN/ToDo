package com.farasatnovruzov.todo.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farasatnovruzov.todo.model.ToDo

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}