package com.chalkboardexam.birthdays.framework.ui.birthdays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.chalkboardexam.birthdays.R
import com.chalkboardexam.birthdays.applevel.ChalkBoardApplication
import com.chalkboardexam.birthdays.businesslogic.domain.state.Response
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateMessageCallback
import com.chalkboardexam.birthdays.businesslogic.domain.state.UIComponentType
import com.chalkboardexam.birthdays.framework.ui.UIController
import com.chalkboardexam.birthdays.framework.ui.common.CustomFragmentFactory
import com.chalkboardexam.birthdays.framework.ui.displayToast
import com.chalkboardexam.birthdays.framework.ui.gone
import com.chalkboardexam.birthdays.framework.ui.visible
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class BirthdaysActivity : AppCompatActivity(), UIController {

    @Inject
    lateinit var fragmentFactory: CustomFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun inject() {
        (application as ChalkBoardApplication).birthdaysComponent.inject(this)
    }

    override fun displayProgressBar(isDisplayed: Boolean) {
        if(isDisplayed)
            main_progress_bar.visible()
        else
            main_progress_bar.gone()
    }

    override fun onResponseReceived(response: Response, stateMessageCallback: StateMessageCallback) {
        when(response.uiComponentType) {
            is UIComponentType.Toast -> {
                response.message?.let {
                    displayToast(it, stateMessageCallback)
                }
            }
            is UIComponentType.Dialog -> {
                //show dialog.
            }
            is UIComponentType.None -> { } //show nothing, may take action based on the situation.
        }
    }
}