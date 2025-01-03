package com.kishorramani.unittesting.quoteapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kishorramani.unittesting.R
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test

class QuoteActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(QuoteActivity::class.java)
    //here we don't have to use intent because QuoteActivity is already launcher activity

    @Test
    fun testNextButton_expectedCorrectQuote() {
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())

        onView(withId(R.id.quoteText)).check(matches(withText("This is quote 4")))
    }

    @Test
    fun testShareButton_expectedIntentChooser() {
        //arrange
        Intents.init()      //initialize intent
        //action
        onView(withId(R.id.floatingActionButton)).perform(click())
        //assert
        val expected = allOf(hasAction(Intent.ACTION_SEND))
        intended(expected)

        Intents.release()       //release intent
    }
}