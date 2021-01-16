package com.chalkboardexam.birthdays.framework.ui.birthdays.birthdays

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday


interface BirthdaysAdapterContract {

    fun onBirthdayClicked(clickedBirthday: Birthday)
}