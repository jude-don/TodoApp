package com.example.todolistapp.domain

sealed interface Routes{
    val  route:String
}
object TodoList:Routes{
    override val route = "todo_list"
}
object AddEditTodo:Routes{
    override val route = "add_edit_todo"
}