package com.chalkboardexam.birthdays.di

import com.chalkboardexam.birthdays.di.birthdays.BirthdaysComponent
import dagger.Module

@Module(
    subcomponents = [
        BirthdaysComponent::class
    ]
)
class SubComponentsModule