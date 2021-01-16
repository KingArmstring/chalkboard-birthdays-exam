package com.chalkboardexam.birthdays.utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class DateUtilTest {


    /**
     * input format: yyyy.MM.dd G 'at' HH:mm:ss z
     * desired format: dd-mm-yyyy
     */
    @Test
    fun dateUtils_randomInputDatesWithNetworkFormat_returnDateWithDesiredFormat() {
        val inputDateExample1 = "1947-01-15T06:43:10.004Z"
        val outputDateExample1 = DateUtil.formatDate(inputDateExample1)
        val expectedDateOutput1 = "15-01-1947"
        assertTrue { outputDateExample1 == expectedDateOutput1}

        val inputDateExample2 = "1962-04-14T03:03:38.410Z"
        val outputDateExample2 = DateUtil.formatDate(inputDateExample2)
        val expectedDateOutput2 = "14-04-1962"
        assertTrue { outputDateExample2 == expectedDateOutput2 }

        val inputDateExample3 = "1981-04-11T03:02:12.434Z"
        val outputDateExample3 = DateUtil.formatDate(inputDateExample3)
        val expectedDateOutput3 = "11-04-1981"
        assertTrue { outputDateExample3 == expectedDateOutput3 }

        val inputDateExample4 = "1974-07-27T15:09:24.071Z"
        val outputDateExample4 = DateUtil.formatDate(inputDateExample4)
        val expectedDateOutput4 = "27-07-1974"
        assertTrue { outputDateExample4 == expectedDateOutput4 }
    }

    @Test
    fun dateUtils_invalidInputDateFormatOrString_invalidDateMessageReturned() {
        val inputDateExample1 = "ahcbaidcnasjahcbaidcnasjahcbaidcnasj"
        val outputDateExample1 = DateUtil.formatDate(inputDateExample1)
        val expectedDateOutput = "Invalid date"
        assertTrue { outputDateExample1 == expectedDateOutput}

        val inputDateExample2 = "1962 04 14T03:03:38.410Z"
        val outputDateExample2 = DateUtil.formatDate(inputDateExample2)
        assertTrue { outputDateExample2 == expectedDateOutput }

        val inputDateExample3 = "1981 04 11"
        val outputDateExample3 = DateUtil.formatDate(inputDateExample3)
        assertTrue { outputDateExample3 == expectedDateOutput }

        val inputDateExample4 = "27 07 1974"
        val outputDateExample4 = DateUtil.formatDate(inputDateExample4)
        assertTrue { outputDateExample4 == expectedDateOutput }
    }
}