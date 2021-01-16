package com.chalkboardexam.birthdays.framework.ui.birthdays.state

import android.os.Parcelable
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import kotlinx.android.parcel.Parcelize


@Parcelize
class BirthdaysViewState(
    var birthdaysList: List<Birthday>? = null, // this list data item will be used to fill the in the first fragment (BirthdaysFragment)
    var currentBirthday: Birthday? = null // this list data item will be used to fill the in the second fragment (UserProfileFragment)
) : Parcelable