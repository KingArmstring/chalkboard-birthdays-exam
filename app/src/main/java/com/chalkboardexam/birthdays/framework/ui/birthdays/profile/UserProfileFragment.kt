package com.chalkboardexam.birthdays.framework.ui.birthdays.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chalkboardexam.birthdays.R
import com.chalkboardexam.birthdays.applevel.ChalkBoardApplication
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateMessageCallback
import com.chalkboardexam.birthdays.framework.ui.birthdays.BaseBirthdaysFragment
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysStateEvent
import com.chalkboardexam.birthdays.utils.Constants.SELECTED_BIRTHDAY_KEY
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment(viewModelFactory: ViewModelProvider.Factory)
    : BaseBirthdaysFragment(R.layout.fragment_user_profile, viewModelFactory) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnGoBackButtonClickedHandler()
        subscribeObservers()
        arguments?.let { args ->
            args.getParcelable<Birthday>(SELECTED_BIRTHDAY_KEY).let { selectedBirthday ->
                selectedBirthday?.let {
                    viewModel.setStateEvent(BirthdaysStateEvent.SetCurrentBirthdayEvent(it))
                }
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.currentBirthday?.let { selectedBirthday ->
                setUI(selectedBirthday)
            }
        }
        viewModel.stateMessage.observe(viewLifecycleOwner) { stateMessage ->
            stateMessage?.response?.let { response ->
                uiController.onResponseReceived(
                    response = response,
                    stateMessageCallback = object : StateMessageCallback {
                        override fun removeMessageFromStack() {
                            viewModel.clearStateMessage()
                        }
                    }
                )
            }
        }
        viewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner) {
            uiController.displayProgressBar(it)
        }
    }

    private fun setUI(selectedBirthday: Birthday) {
        val firstLetters = selectedBirthday.name.first[0].toUpperCase()
        user_logo_first_letters.text = firstLetters.toString()

        val username = "${selectedBirthday.name.first} ${selectedBirthday.name.last}"
        user_name.text = username

        user_age.text = selectedBirthday.dob.age.toString()
    }

    override fun inject() {
        activity?.run {
            (application as ChalkBoardApplication).birthdaysComponent.inject(this@UserProfileFragment)
        }?: throw Exception("BirthdaysActivity is null")
    }

    private fun setOnGoBackButtonClickedHandler() {
        btn_go_back.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_birthdaysFragment)
        }
    }
}