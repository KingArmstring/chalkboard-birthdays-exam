package com.chalkboardexam.birthdays.framework.ui.birthdays.state

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateEvent
import com.chalkboardexam.birthdays.utils.Constants

class BirthdaysStateEvent {

    class GetAllBirthdaysEvent: StateEvent {

        // this will be used to hold any error that might happen.
        override fun errorInfo() = Constants.ERROR_FETCHING_BIRTHDAYS

        // this will be used to keep track of the StateEvent by the StateEventManager.
        override fun eventName() = Constants.GET_BIRTHDAYS_STATE_EVENT

        override fun shouldDisplayProgressBar() = true
    }

    class SetCurrentBirthdayEvent(val selectedBirthday: Birthday): StateEvent {

        // this will be used to hold any error that might happen.
        override fun errorInfo() = Constants.ERROR_SETTING_CURRENT_SELECTED_BIRTHDAY

        // this will be used to keep track of the StateEvent by the StateEventManager.
        override fun eventName() = Constants.SET_SELECTED_BIRTHDAY_STATE_EVENT

        override fun shouldDisplayProgressBar() = true
    }
}