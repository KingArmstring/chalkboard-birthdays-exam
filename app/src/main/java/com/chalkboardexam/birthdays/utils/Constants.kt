package com.chalkboardexam.birthdays.utils

object Constants {

    const val BASE_URL: String = "https://randomuser.me/"

    //GetAllBirthdays use-case constants
    const val ERROR_FETCHING_BIRTHDAYS = "Error fetching Birthdays from server, please try again later"
    const val GET_BIRTHDAYS_STATE_EVENT = "GetAllBirthdaysStateEvent"
    const val SET_SELECTED_BIRTHDAY_STATE_EVENT = "SetSelectedBirthdayStateEvent"
    const val ERROR_SETTING_CURRENT_SELECTED_BIRTHDAY = "Error selecting current birthday"

    //ui constants
    const val BIRTHDAYS_LOADED_SUCCESSFULLY = "Birthdays loaded!"
    const val UNKNOWN_ERROR = "Unknown error"
}