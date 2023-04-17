package com.example.todolistapp.data

import kotlinx.coroutines.flow.Flow


interface TodoRepository {

    suspend fun inserTodo(localTodo: Todo)


    suspend fun deleteTodo(todo: Todo)


    suspend fun getTodoById(id:Int):Todo?

    fun getTodos(): Flow<List<Todo>>
    /** Flows means we get realtime updates as soon as our database changes */
}