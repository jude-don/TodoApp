package com.example.todolistapp.ui

data class TodoListScreenState(
    val toDo: List<Todo>,
    val isLoading:Boolean,
    val error:String? = null
)
