package com.example.todolistapp.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.data.Todo
import com.example.todolistapp.domain.UiEvent

@Composable
fun ToDoScreen(
    onNavigate:(UiEvent.Navigate)->Unit,
    viewModel: TodoListViewModel = hiltViewModel()
){
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(TodoListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> {
                    onNavigate(event)

                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
            title = {
                Text(
                    text = "My Tasks",
                    color = Color.White
                )
            },
            backgroundColor = Color.Green
        )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { 
                    viewModel.onEvent(TodoListEvent.OnAddTodoClick)
                }
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
       LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
           items(todos){todo ->
               TodoItem(
                   todo = todo,
                   onEvent = viewModel::onEvent,
                   modifier = Modifier
                       .fillMaxWidth()
                       .clickable {
                           viewModel.onEvent(TodoListEvent.OnTodoClick(todo))
                       }
                       .padding(16.dp)
               )

           }

        }

    }
}



@Composable
private fun TodoItem(
    todo:Todo,
    onEvent:(TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        onEvent(TodoListEvent.DeleteTodo(todo))
                    }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Button"
                    )
                    
                }
            }
            todo.description?.let {
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Text(
                    text = it
                )
            }

        }
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = {
                onEvent(TodoListEvent.OnDoneChange(todo,it))
            }
        )

    }

}