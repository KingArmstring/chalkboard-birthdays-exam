package com.chalkboardexam.birthdays.framework.ui.birthdays

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseBirthdaysFragment(
    @LayoutRes private val layoutRes: Int,
    viewModelFactory: ViewModelProvider.Factory
) : Fragment() {
}