package com.chalkboardexam.birthdays.businesslogic.data.network.implementation

import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday

class BirthdaysNetworkDataSourceImpl : BirthdaysNetworkDataSource {

    override suspend fun getBirthdaysList(): List<Birthday> {
        TODO("Not yet implemented")
    }

}