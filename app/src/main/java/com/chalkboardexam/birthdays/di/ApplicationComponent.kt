package com.chalkboardexam.birthdays.di

import com.chalkboardexam.birthdays.applevel.ChalkBoardApplication
import com.chalkboardexam.birthdays.di.birthdays.BirthdaysComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        SubComponentsModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: ChalkBoardApplication): ApplicationComponent
    }

    fun birthdaysComponent() : BirthdaysComponent.Factory
}