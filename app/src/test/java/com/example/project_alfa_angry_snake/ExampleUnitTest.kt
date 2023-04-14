package com.example.project_alfa_angry_snake

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val sdf = SimpleDateFormat("dMMyHmmss")
        val currentDateAndTime = sdf.format(Date())
        print(currentDateAndTime)
    }
}