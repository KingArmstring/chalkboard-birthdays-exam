package com.chalkboardexam.birthdays.applevel

import android.app.Application
import android.util.Log
import com.chalkboardexam.birthdays.di.ApplicationComponent
import com.chalkboardexam.birthdays.di.DaggerApplicationComponent
import com.chalkboardexam.birthdays.di.birthdays.BirthdaysComponent

class ChalkBoardApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent

    lateinit var birthdaysComponent: BirthdaysComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .factory()
            .create(this)
        birthdaysComponent = applicationComponent.birthdaysComponent().create()
    }
}