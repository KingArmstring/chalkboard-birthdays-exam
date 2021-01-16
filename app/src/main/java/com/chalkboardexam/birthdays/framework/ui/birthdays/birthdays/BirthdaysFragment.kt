package com.chalkboardexam.birthdays.framework.ui.birthdays.birthdays

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalkboardexam.birthdays.R
import com.chalkboardexam.birthdays.applevel.ChalkBoardApplication
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.businesslogic.domain.state.StateMessageCallback
import com.chalkboardexam.birthdays.framework.ui.birthdays.BaseBirthdaysFragment
import com.chalkboardexam.birthdays.framework.ui.birthdays.state.BirthdaysStateEvent
import com.chalkboardexam.birthdays.utils.Constants.SELECTED_BIRTHDAY_KEY
import kotlinx.android.synthetic.main.fragment_birthdays.*

class BirthdaysFragment(viewModelFactory: ViewModelProvider.Factory)
    : BaseBirthdaysFragment(R.layout.fragment_birthdays, viewModelFactory), BirthdaysAdapterContract {


    lateinit var adapter: BirthdaysAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiateBirthdaysList()
        subscribeObservers()
        if(savedInstanceState == null) {
            viewModel.setStateEvent(BirthdaysStateEvent.GetAllBirthdaysEvent())
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.birthdaysList?.let { birthdays ->
                if (birthdays.isNotEmpty()) {
                    adapter.differ.submitList(birthdays)
                }
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

    override fun onBirthdayClicked(clickedBirthday: Birthday) {
        val args = bundleOf(Pair(SELECTED_BIRTHDAY_KEY, clickedBirthday))
        findNavController().navigate(R.id.action_birthdaysFragment_to_userProfileFragment, args)
    }

    private fun instantiateBirthdaysList() {
        adapter = BirthdaysAdapter(this)
        birthdays_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        birthdays_recyclerview.adapter = adapter
    }

    override fun inject() {
        activity?.run {
            (application as ChalkBoardApplication).birthdaysComponent.inject(this@BirthdaysFragment)
        }?: throw Exception("BirthdaysActivity is null")
    }

}