package com.example.todolistapp.domain

import android.os.Message

sealed class UiEvent{  /** These are all the event handlers for the various events */
    object PopBackStack:UiEvent() /** This for when we want to navigate back*/
    data class Navigate(val route:String):UiEvent() /** This is an event we will use to navigate to a particular route*/
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ):UiEvent() /** This will take the message we want to show in the UI and will take an option action to be perfomed */
}


//data class myUiEvent(
//
//)
