package com.chalkboardexam.birthdays.businesslogic.data.network

import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import java.util.*

class BirthdaysFakeDataSource : BirthdaysNetworkDataSource {

    override suspend fun getBirthdaysList(): List<Birthday> {
        val birthdays = mutableListOf<Birthday>()
        for (i in 0..9) {
            birthdays.add(createSingleBirthday())
        }
        return birthdays
    }

    private fun createSingleBirthday() = Birthday(
        Birthday.Name(title = UUID.randomUUID().toString(),
            first = UUID.randomUUID().toString(),
            last = UUID.randomUUID().toString()),
        Birthday.DOB(date = UUID.randomUUID().toString(),
            age = (10..50).random())
    )
}