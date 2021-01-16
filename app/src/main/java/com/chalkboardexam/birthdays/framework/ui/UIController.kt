package com.chalkboardexam.birthdays.framework.ui

import com.chalkboardexam.birthdays.businesslogic.domain.state.Response
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateMessageCallback

interface UIController {

    fun displayProgressBar(isDisplayed: Boolean)

    fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    )
}