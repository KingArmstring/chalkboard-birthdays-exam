package com.chalkboardexam.birthdays.framework.ui.birthdays

import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.businesslogic.domain.state.DataState
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateEvent
import com.chalkboardexam.birthdays.businesslogic.interactors.birthdays.GetAllBirthdays
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysStateEvent
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysViewState
import com.chalkboardexam.birthdays.framework.ui.common.BaseViewModel
import kotlinx.coroutines.flow.Flow


class BirthdaysViewModel (
    val getAllBirthdays: GetAllBirthdays
)
    : BaseViewModel<BirthdaysViewState>(){

    init {
        setupChannel()
    }

    override fun handleNewData(viewState: BirthdaysViewState) {
        viewState.birthdaysList?.let { birthdays ->
            setBirthdaysListData(birthdays)
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {

        when(stateEvent) {
            is BirthdaysStateEvent.GetAllBirthdaysEvent -> {
                val job: Flow<DataState<BirthdaysViewState>?> = getAllBirthdays.getAllBirthdays(stateEvent)
                launchJob(stateEvent, job)
            }
            is BirthdaysStateEvent.SetCurrentBirthdayEvent -> {
                setSelectedBirthday(stateEvent.selectedBirthday)
            }
        }

    }

    override fun initNewViewState(): BirthdaysViewState {
        return BirthdaysViewState()
    }

    private fun setBirthdaysListData(birthdays: List<Birthday>) {
        val update = getCurrentViewStateOrNew()
        update.birthdaysList = birthdays
        setViewState(update)
    }

    private fun setSelectedBirthday(selectedBirthday: Birthday) {
        val update = getCurrentViewStateOrNew()
        update.currentBirthday = selectedBirthday
        setViewState(update)
    }

}