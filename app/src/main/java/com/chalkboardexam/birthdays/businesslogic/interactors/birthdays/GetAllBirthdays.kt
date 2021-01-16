package com.chalkboardexam.birthdays.businesslogic.interactors.birthdays

import com.chalkboardexam.birthdays.businesslogic.data.network.ApiResponseHandler
import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.data.util.safeApiCall
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.businesslogic.domain.state.*
import com.chalkboardexam.birthdays.di.birthdays.BirthdaysScope
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysViewState
import com.chalkboardexam.birthdays.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@BirthdaysScope
class GetAllBirthdays
@Inject
constructor(
    private val birthdaysNetworkDataSource: BirthdaysNetworkDataSource
) {
    fun getAllBirthdays(stateEvent: StateEvent): Flow<DataState<BirthdaysViewState>?> = flow {
        val birthdaysListApiResult = safeApiCall(Dispatchers.IO) {
            val birthdaysList: List<Birthday>
            try {
                birthdaysList = birthdaysNetworkDataSource.getBirthdaysList()
            }catch (e: Exception) {
                return@safeApiCall null
            }
            return@safeApiCall birthdaysList
        }

        val birthdaysApiResponseHandler = object: ApiResponseHandler<BirthdaysViewState, List<Birthday>>(
            response = birthdaysListApiResult,
            stateEvent = stateEvent
        ) {

            override suspend fun handleSuccess(data: List<Birthday>): DataState<BirthdaysViewState>? {
                return if (data.isNotEmpty()) {
                    DataState.data(
                        response = Response(
                            message = Constants.BIRTHDAYS_LOADED_SUCCESSFULLY,
                            uiComponentType = UIComponentType.Toast(),
                            messageType = MessageType.Success()
                        ),
                        data = BirthdaysViewState(data),
                        stateEvent = stateEvent
                    )
                }else {
                    DataState.data(
                        response = Response(
                            message = Constants.UNKNOWN_ERROR,
                            uiComponentType = UIComponentType.Toast(), // we don't have always to tell user that error happened, it's a requirements or project owner decision.
                            messageType = MessageType.Error()
                        ),
                        data = BirthdaysViewState(data),
                        stateEvent = stateEvent
                    )
                }
            }

        }.getResult()
        emit(birthdaysApiResponseHandler)

    }
}
