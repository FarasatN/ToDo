package com.farasatnovruzov.todo.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.farasatnovruzov.todo.model.ToDo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ToDoDao {

//    @Query("SELECT * FROM ToDo")
//    fun getAll(): Flowable<List<ToDo>>

    @Query("SELECT title,id,added_time FROM ToDo")
    fun getToDoWithNameIdAndTime(): Flowable<List<ToDo>>


    @Query("SELECT * FROM ToDo WHERE id = :id")
    fun getToDoById(id: Int): Flowable<ToDo>

    @Insert
    fun insert(toDo: ToDo): Completable

    @Delete
    fun delete(toDo: ToDo): Completable
}
