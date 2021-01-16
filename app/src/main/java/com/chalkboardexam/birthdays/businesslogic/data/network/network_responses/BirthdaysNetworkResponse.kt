package com.chalkboardexam.birthdays.businesslogic.data.network.network_responses

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday

data class BirthdaysNetworkResponse(val results: List<Birthday>)