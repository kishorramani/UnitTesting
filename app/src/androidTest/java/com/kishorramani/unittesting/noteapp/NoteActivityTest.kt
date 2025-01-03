package com.kishorramani.unittesting.noteapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kishorramani.unittesting.R
import org.junit.Rule
import org.junit.Test

class NoteActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(NoteActivity::class.java)

    @Test
    fun testSubmitButton_expectedCorrectValue() {
        onView(withId(R.id.etTitle)).perform(typeText("Hello"))
        onView(withId(R.id.etDescription)).perform(typeText("Kishor Ramani"), closeSoftKeyboard())

        onView(withId(R.id.btSubmit)).perform(click())

        val expectedMsg = "Title - Hello | Description - Kishor Ramani"
        onView(withId(R.id.tvMsg)).check(matches(withText(expectedMsg)))
    }
}