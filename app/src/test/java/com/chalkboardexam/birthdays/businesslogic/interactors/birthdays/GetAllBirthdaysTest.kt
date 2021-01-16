package com.chalkboardexam.birthdays.businesslogic.interactors.birthdays

import com.chalkboardexam.birthdays.businesslogic.data.network.BirthdaysFakeDataSource
import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.domain.state.MessageType
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysStateEvent
import com.chalkboardexam.birthdays.utils.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class GetAllBirthdaysTest {

    //system under test
    private val getAllBirthdays: GetAllBirthdays

    //dependencies
    private val birthdaysNetworkDataSource: BirthdaysNetworkDataSource

    init {
        birthdaysNetworkDataSource = BirthdaysFakeDataSource()
        getAllBirthdays = GetAllBirthdays(birthdaysNetworkDataSource)
    }

    /**
     * Disclaimer, because I follow TDD style(meaning I write the test before the implementation)
     * and because I here in this test am collecting a flow, and non has been emitted yet
     * (because the use-case has not been implemented yet) this test will succeed without getting
     * inside the collection callback, we need to put this into consideration or we will think it's
     * working.
     */
    @Test
    fun getAllBirthdays_success_expectedEmitted10BirthdaysFromFlow() = runBlocking {
        getAllBirthdays.getAllBirthdays(BirthdaysStateEvent.GetAllBirthdaysEvent())
            .collect { value ->
                assertEquals(value?.stateMessage?.response?.message?: "", Constants.BIRTHDAYS_LOADED_SUCCESSFULLY)
                assertTrue { value?.data?.birthdaysList?.size ?: 0 == 10 }
                assertTrue { value?.stateMessage?.response?.messageType is MessageType.Success }
            }
    }

    //unfortunately this is the only case we can test now because this screen is very simple but in
    //real application when we have things like addNewBirthday, deleteBirthday, undoDeleteBirthday,
    //editBirthday, ...etc surly we can test this screen in much more robust way.

}