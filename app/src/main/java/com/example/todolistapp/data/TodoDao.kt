package com.example.todolistapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTodo(localTodo: Todo)

    @Delete
    suspend fun deleteTodo(localTodo: Todo)

    @Query("SELECT * FROM todo WHERE id= :id")
    suspend fun getTodoById(id:Int):Todo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
    /** Flows means we get realtime updates as soon as our database changes */
}