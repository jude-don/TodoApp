package com.example.todolistapp.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.Todo
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.domain.AddEditTodo
import com.example.todolistapp.domain.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repo:TodoRepository
):ViewModel(){
    val todos = repo.getTodos()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent =  _uiEvent.receiveAsFlow()
    private var deletedTodo: Todo? = null







    fun onEvent(event: TodoListEvent){ /** This function is used to handle all events that will be applied instead of having
                                           a separate function to handle everything */
        when (event){
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(AddEditTodo.route + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(AddEditTodo.route ))
            }
            is TodoListEvent.OnUndoDeleteClick ->{
                deletedTodo?.let {
                   viewModelScope.launch {
                       repo.inserTodo(it)
                   }
                }

            }
            is TodoListEvent.DeleteTodo -> {
                deletedTodo = event.todo
                viewModelScope.launch {
                    repo.deleteTodo(event.todo)
                }
                sendUiEvent(UiEvent.ShowSnackbar(
                    message = "Todo deleted",
                    action = "Undo"
                ))

            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repo.inserTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }

            }
        }
    }


    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event) /** This is to send something to the channel we set earlier  */
//            _uiEvent.send(UiEvent.Navigate(AddEditTodo.route)) /** This is to send something to the channel we set earlier  */
        }
    }



}