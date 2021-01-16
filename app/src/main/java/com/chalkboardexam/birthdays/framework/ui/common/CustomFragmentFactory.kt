package com.chalkboardexam.birthdays.framework.ui.common

import androidx.fragment.app.FragmentFactory
import com.chalkboardexam.birthdays.di.birthdays.BirthdaysScope
import com.chalkboardexam.birthdays.framework.ui.birthdays.birthdays.BirthdaysFragment
import com.chalkboardexam.birthdays.framework.ui.birthdays.profile.UserProfileFragment
import javax.inject.Inject

@BirthdaysScope
class CustomFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelFactory,
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            BirthdaysFragment::class.java.name -> {
                val fragment = BirthdaysFragment(viewModelFactory)
                fragment
            }

            UserProfileFragment::class.java.name -> {
                val fragment = UserProfileFragment(viewModelFactory)
                fragment
            }

            //PLACEHOLDER for other fragments if any.

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}