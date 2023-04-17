package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistapp.domain.AddEditTodo
import com.example.todolistapp.domain.TodoList
import com.example.todolistapp.ui.add_edit_todo.AddEditTodoScreen
import com.example.todolistapp.ui.theme.TodoListAppTheme
import com.example.todolistapp.ui.todo_list.ToDoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                     val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = TodoList.route){
                        composable(TodoList.route){
                            ToDoScreen(onNavigate ={
                                navController.navigate(it.route) /** It here represents the parameter that is passed*/
                            } )
                        }
                        composable(
                            route = AddEditTodo.route + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name ="todoId"){
                                    type = NavType.IntType
                                    defaultValue = -1 /** This -1 is used to signify that no id was passed */
                                }
                            )
                        ){
                            AddEditTodoScreen(onPopBackStack = {
                                navController.popBackStack()
                            })

                        }
                    }

                }
            }
        }
    }
}
