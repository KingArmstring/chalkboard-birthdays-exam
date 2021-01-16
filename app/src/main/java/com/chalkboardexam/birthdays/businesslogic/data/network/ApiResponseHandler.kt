package com.chalkboardexam.birthdays.businesslogic.data.network

import com.chalkboardexam.birthdays.businesslogic.domain.state.*


abstract class ApiResponseHandler <ViewState, Data>(
    private val response: ApiResult<Data?>,
    private val stateEvent: StateEvent?
){

    suspend fun getResult(): DataState<ViewState>? {

        return when(response){

            is ApiResult.Success -> {
                if(response.value == null){
                    DataState.error(
                        response = Response(
                            message = stateEvent?.errorInfo(),
                            uiComponentType = UIComponentType.Toast(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                }
                else{
                    handleSuccess(data = response.value)
                }
            }

            is ApiResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = stateEvent?.errorInfo(),
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

        }
    }

    abstract suspend fun handleSuccess(data: Data): DataState<ViewState>?

}