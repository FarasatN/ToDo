package com.farasatnovruzov.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDo(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "added_time")
    var added_time: String?,

    )
//    :Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
