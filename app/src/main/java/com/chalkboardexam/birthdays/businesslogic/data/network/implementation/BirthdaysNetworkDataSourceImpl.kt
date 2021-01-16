package com.chalkboardexam.birthdays.businesslogic.data.network.implementation

import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.framework.data.network.abstraction.BirthdaysService

class BirthdaysNetworkDataSourceImpl(
    private val birthdaysService: BirthdaysService
) : BirthdaysNetworkDataSource {

    override suspend fun getBirthdaysList(): List<Birthday> {
        return birthdaysService.getBirthdaysList()
    }
}