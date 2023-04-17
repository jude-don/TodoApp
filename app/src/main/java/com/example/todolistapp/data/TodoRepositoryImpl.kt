package com.example.todolistapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

//@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun inserTodo(newlocalTodo: Todo) {
        withContext(Dispatchers.IO){
            dao.inserTodo(newlocalTodo)
        }
    }

    override suspend fun deleteTodo(newlocalTodo: Todo) {
        withContext(Dispatchers.IO){
            dao.deleteTodo(newlocalTodo)
        }
    }

    override suspend fun getTodoById(id: Int): Todo? {
       return withContext(Dispatchers.IO){
           dao.getTodoById(id)
       }
    }

    override fun getTodos(): Flow<List<Todo>> {
          return dao.getTodos()
    }

}