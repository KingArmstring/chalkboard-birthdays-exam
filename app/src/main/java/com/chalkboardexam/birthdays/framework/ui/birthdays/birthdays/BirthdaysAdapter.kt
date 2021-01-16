package com.chalkboardexam.birthdays.framework.ui.birthdays.birthdays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chalkboardexam.birthdays.R
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import com.chalkboardexam.birthdays.utils.DateUtil


class BirthdaysAdapter(
    private val callback: BirthdaysAdapterContract
): RecyclerView.Adapter<BirthdaysAdapter.BirthdayViewHolder>() {

    var diffUtilsCallBack: DiffUtil.ItemCallback<Birthday> =
        object : DiffUtil.ItemCallback<Birthday>() {
            override fun areItemsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Birthday, newItem: Birthday): Boolean {
                return oldItem == newItem
            }
        }

    var differ = AsyncListDiffer(this, diffUtilsCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BirthdayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.birthday_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) =
        holder.bind(differ.currentList[position], callback)


    override fun getItemCount() = differ.currentList.size



    class BirthdayViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val profileLetters = itemView.findViewById<TextView>(R.id.list_item_profile_letters)
        val username = itemView.findViewById<TextView>(R.id.list_item_username)
        val dateOfBirth = itemView.findViewById<TextView>(R.id.list_item_date_of_birth)

        fun bind(birthday: Birthday, callback: BirthdaysAdapterContract) {
            profileLetters.text = getFirstLetters(birthday.name.first, birthday.name.last)
            val fullName = "${birthday.name.first} ${birthday.name.last}"
            username.text = fullName
            dateOfBirth.text = DateUtil.formatDate(birthday.dob.date)
            itemView.setOnClickListener {
                callback.onBirthdayClicked(birthday)
            }
        }

        private fun getFirstLetters(first: String, last: String) : String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(if (first.isNotEmpty()) first[0].toUpperCase() else "")
            stringBuilder.append(if (last.isNotEmpty()) last[0].toUpperCase() else "")
            return stringBuilder.toString()
        }
    }
}
