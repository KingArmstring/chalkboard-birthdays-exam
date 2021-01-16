package com.chalkboardexam.birthdays.framework.ui.birthdays

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.chalkboardexam.birthdays.framework.ui.UIController

abstract class BaseBirthdaysFragment(
    @LayoutRes private val layoutRes: Int,
    viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    val viewModel: BirthdaysViewModel by viewModels {
        viewModelFactory
    }

    lateinit var uiController: UIController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
        setUIController()
    }

    fun setUIController() {
        activity?.let {
            // because I have only one Activity I used it here to save myself some time, but in
            // general I would create a BaseActivity to collect all activities common tasks.
            if(it is BirthdaysActivity){
                try{
                    uiController = context as UIController
                }catch (e: ClassCastException){
                    e.printStackTrace()
                }
            }
        }
    }
}