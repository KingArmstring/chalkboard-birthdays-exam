package com.chalkboardexam.birthdays.framework.data.network.api

import com.chalkboardexam.birthdays.businesslogic.data.network.network_responses.BirthdaysNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ChalkboardApi {
    @GET
    suspend fun getBirthdaysList(
        @Url url: String = "api/",
        @Query("results") results: Int = 1000,
        @Query("seed") seed: String = "chalkboard",
        @Query("inc") name: String = "name,dob"
    ): BirthdaysNetworkResponse
}