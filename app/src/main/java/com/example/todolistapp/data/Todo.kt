package com.example.todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey
    val id:Int? = null,
    val title:String,
    val description:String?,
    val isDone: Boolean

)
