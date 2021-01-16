package com.chalkboardexam.birthdays.framework.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chalkboardexam.birthdays.businesslogic.domain.state.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//No need to create BaseViewModel we can just create BirthdaysViewModel directly because this is the
//only ViewModel because BirthdaysFragment is the only UI component out there but I decided to make it
//like so to show how it should be done in bigger projects.
abstract class BaseViewModel<ViewState> : ViewModel() {
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()

    val dataChannelManager: DataChannelManager<ViewState> = object : DataChannelManager<ViewState>() {

        override fun handleNewData(data: ViewState) {
            this@BaseViewModel.handleNewData(data)
        }
    }

    val viewState: LiveData<ViewState>
        get() = _viewState

    val shouldDisplayProgressBar: LiveData<Boolean> = dataChannelManager.shouldDisplayProgressBar

    val stateMessage: LiveData<StateMessage?>
        get() = dataChannelManager.messageStack.stateMessage

    fun setupChannel() = dataChannelManager.setupChannel()

    abstract fun handleNewData(viewState: ViewState)

    abstract fun setStateEvent(stateEvent: StateEvent)

    fun emitStateMessageEvent(
        stateMessage: StateMessage,
        stateEvent: StateEvent
    ) = flow {
        emit(
            DataState.error<ViewState>(
                response = stateMessage.response,
                stateEvent = stateEvent
            )
        )
    }

    fun emitInvalidStateEvent(stateEvent: StateEvent) = flow {
        emit(
            DataState.error<ViewState>(
                response = Response(
                    message = "Invalid state event",
                    uiComponentType = UIComponentType.None(),
                    messageType = MessageType.Error()
                ),
                stateEvent = stateEvent
            )
        )
    }

    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<ViewState>?>
    ) = dataChannelManager.launchJob(stateEvent, jobFunction)

    fun getCurrentViewStateOrNew(): ViewState {
        return viewState.value ?: initNewViewState()
    }

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }

    fun clearStateMessage(index: Int = 0) {
        dataChannelManager.clearStateMessage(index)
    }

    fun clearActiveStateEvents() = dataChannelManager.clearActiveStateEventCounter()

    fun clearAllStateMessages() = dataChannelManager.clearAllStateMessages()

    fun cancelActiveJobs() = dataChannelManager.cancelJobs()

    abstract fun initNewViewState(): ViewState

}