package com.chalkboardexam.birthdays.businesslogic.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Birthday(
    val name: Name,
    val dob: DOB
) : Parcelable {

    @Parcelize
    data class Name(val title: String, val first: String, val last: String): Parcelable

    @Parcelize
    data class DOB(val date: String, val age: Int): Parcelable
}