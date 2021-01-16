package com.chalkboardexam.birthdays.framework.ui

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateMessageCallback


//These are some extension function to View and Activity classes, leveraging the kotlin extension funs.

/**
 * called on the view to make it GONE
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Called on the view to make it VISIBLE
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Can be called on inside Activities to show toast
 */
fun Activity.displayToast(
    message:String,
    stateMessageCallback: StateMessageCallback
){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    stateMessageCallback.removeMessageFromStack()
}

//In larger application we can add more extension functions like dialog(), invisible(), animation functions, ..etc