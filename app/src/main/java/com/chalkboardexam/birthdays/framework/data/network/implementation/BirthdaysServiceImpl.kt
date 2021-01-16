package com.chalkboardexam.birthdays.framework.data.network.implementation

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.framework.data.network.abstraction.BirthdaysService
import com.chalkboardexam.birthdays.framework.data.network.api.ChalkboardApi

class BirthdaysServiceImpl(private val api: ChalkboardApi): BirthdaysService {
    override suspend fun getBirthdaysList(
        results: String,
        seed: String,
        inc: String
    ): List<Birthday> {
        return api.getBirthdaysList(
        ).results
    }
}