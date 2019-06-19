package com.github.gibbrich.dogs.ui.activity

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.dogs.DogsApp
import com.github.gibbrich.dogs.R
import com.github.gibbrich.dogs.TestDogsApp
import com.github.gibbrich.dogs.di.AppComponentMock
import com.github.gibbrich.dogs.di.DI
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val rule = ActivityTestRule(
        MainActivity::class.java,
        true,
        false
    )

    @Inject
    internal lateinit var dogsRepository: DogsRepository

    private val app by lazy {
        InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as TestDogsApp
    }

    @Before
    fun setUp() {
        (DI.appComponent as AppComponentMock).inject(this)
    }

    @Test
    fun no_data_label_displayed_on_MainActivity_start() {
        rule.launchActivity(Intent())

        val context = InstrumentationRegistry.getTargetContext()
        onView(withText(context.getString(R.string.activity_main_no_data))).check(matches(isDisplayed()))
    }
}