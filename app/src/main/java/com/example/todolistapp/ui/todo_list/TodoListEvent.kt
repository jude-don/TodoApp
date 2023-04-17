package com.example.todolistapp.ui.todo_list

import com.example.todolistapp.data.Todo

sealed class TodoListEvent{ /** These are all the event handlers for the various events */

    data class DeleteTodo( /** This is to Delete a To do */
        val todo:Todo
    ):TodoListEvent()

    data class OnDoneChange(/** This is check that a task is Done */
        val todo: Todo,
                            val isDone:Boolean
        ):TodoListEvent()

    object OnUndoDeleteClick:TodoListEvent() /** This is to undo a deleted To do */

    data class OnTodoClick( /** This is to Add a new task to the To do */
        val todo: Todo
    ) :TodoListEvent()

    object OnAddTodoClick:TodoListEvent()
}
