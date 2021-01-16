package com.chalkboardexam.birthdays.di.birthdays

import com.chalkboardexam.birthdays.framework.ui.birthdays.BirthdaysActivity
import com.chalkboardexam.birthdays.framework.ui.birthdays.BirthdaysFragment
import com.chalkboardexam.birthdays.framework.ui.birthdays.UserProfileFragment
import dagger.Subcomponent

@BirthdaysScope
@Subcomponent(modules = [BirthdaysModule::class])
interface BirthdaysComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create() : BirthdaysComponent
    }

    //injecting activity
    fun inject(birthdaysActivity: BirthdaysActivity)

    //injecting all children fragments because I think it does not really need to create a different
    //scope and injection level for inner fragments
    fun inject(birthdaysFragment: BirthdaysFragment)
    fun inject(userProfileFragment: UserProfileFragment)
}