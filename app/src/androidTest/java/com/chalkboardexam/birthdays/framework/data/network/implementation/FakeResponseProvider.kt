package com.chalkboardexam.birthdays.framework.data.network.implementation

import android.content.Context
import com.chalkboardexam.birthdays.businesslogic.domain.model.Birthday
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class FakeResponseProvider {

    fun readFakeData(context: Context) : String {
        val stringBuilder = StringBuilder()
        val inputStream = context.assets?.open("fake_data.json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var str = reader.readLine()
        while (str != null) {
            stringBuilder.append(str)
            str = reader.readLine()
        }
        val jsonObject = JSONObject(stringBuilder.toString())
        return jsonObject.toString()
    }

    fun parseResponse(response: String) : List<Birthday> {
        val jsonResponse = JSONObject(response)
        val birthdaysJsonArray = jsonResponse["results"] as JSONArray

        val birthdays: ArrayList<Birthday> = arrayListOf()

        for(i in 0 until birthdaysJsonArray.length()) {
            birthdays.add(
                Birthday(
                    name = getNameObject((birthdaysJsonArray[i] as JSONObject)["name"] as JSONObject),
                    dob = getDOBObject((birthdaysJsonArray[i] as JSONObject)["dob"] as JSONObject)
                )
            )
        }
        return birthdays
    }

    private fun getNameObject(nameJson: JSONObject) = Birthday.Name(
        title = nameJson["title"] as String,
        first = nameJson["first"] as String,
        last = nameJson["last"] as String
    )

    private fun getDOBObject(dobJsonObject: JSONObject) = Birthday.DOB(
        date = dobJsonObject["date"] as String,
        age = dobJsonObject["age"] as Int
    )
}