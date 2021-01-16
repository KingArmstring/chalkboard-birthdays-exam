package com.chalkboardexam.birthdays.framework.data.network.abstraction

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday

interface BirthdaysService {
    suspend fun getBirthdaysList(
        results: String = "1000",
        seed: String = "chalkboard",
        inc: String = "name,dob"
    ) : List<Birthday>
}