package com.chalkboardexam.birthdays.businesslogic.data.network.abstraction

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday

interface BirthdaysNetworkDataSource {

    suspend fun getBirthdaysList() : List<Birthday>
}