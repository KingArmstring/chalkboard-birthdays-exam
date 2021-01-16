package com.chalkboardexam.birthdays.framework.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chalkboardexam.birthdays.businesslogic.interactors.birthdays.GetAllBirthdays
import com.chalkboardexam.birthdays.di.birthdays.BirthdaysScope
import com.chalkboardexam.birthdays.framework.ui.birthdays.BirthdaysViewModel
import javax.inject.Inject

@BirthdaysScope
class ViewModelFactory
@Inject
constructor(
    private val getAllBirthdays: GetAllBirthdays
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){

            BirthdaysViewModel::class.java -> {
                BirthdaysViewModel(
                    getAllBirthdays = getAllBirthdays
                ) as T
            }
            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }

}
