package com.chalkboardexam.birthdays.framework.data.network.implementation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.framework.data.network.abstraction.BirthdaysService
import com.chalkboardexam.birthdays.framework.data.network.api.ChalkboardApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BirthdaysServiceImplTest {

    //system under test
    private lateinit var birthdaysService: BirthdaysService

    //dependencies
    private lateinit var api: ChalkboardApi

    //helper dependencies
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var fakeResponseProvider: FakeResponseProvider
    private val server = MockWebServer()

    @Before
    fun setup() {
        fakeResponseProvider = FakeResponseProvider()
        server.start()
        api = initializeApi()
        birthdaysService = BirthdaysServiceImpl(api)
    }

    private fun initializeApi(): ChalkboardApi {

        val baseUrl: HttpUrl = server.url("api/?results=1000&seed=chalkboard&inc=name,dob")

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofitBuilder.create(ChalkboardApi::class.java)
    }

    @Test
    fun birthdaysService_correctContent_getAllBirthdaysAsExpected() {

        runBlocking {
            //preparing the correct response for the api.
            val response = MockResponse().setBody(fakeResponseProvider.readFakeData(context))

            val expectedBirthdays: List<Birthday> = response.getBody()?.readUtf8()?.let {
                fakeResponseProvider.parseResponse(it)
            } ?: listOf()

            //teaching the server the response.
            server.enqueue(response)

            //using our api to call the server and make sure it does that correctly, we absolutely
            //can do more testing like changing values of our fake data and make sure it returns them
            //correctly and surely there will be more stuff to be tested when there are more apis in
            //a real application
            val actualBirthdays = birthdaysService.getBirthdaysList()

            //asserting size is the same
            assert(expectedBirthdays.size == actualBirthdays.size)

            //asserting items are the same.
            //we are capable of using "==" because Birthday is data class.
            for (i in expectedBirthdays.indices) {
                kotlin.test.assertTrue { actualBirthdays[i] == expectedBirthdays[i] }
            }
        }

    }

    @After
    fun clean() {
        server.shutdown()
    }
}