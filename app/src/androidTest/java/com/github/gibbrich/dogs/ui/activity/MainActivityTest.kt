package com.github.gibbrich.dogs.ui.activity

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.core.repository.DogsRepository
import com.github.gibbrich.dogs.R
import com.github.gibbrich.dogs.di.AppComponentMock
import com.github.gibbrich.dogs.di.DI
import com.github.gibbrich.dogs.ui.adapter.DogsAdapter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
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

    private val breeds: List<Breed> = listOf(
        Breed("Bull mastiff", "https://images.dog.ceo/breeds/mastiff-bull/n02108422_3795.jpg"),
        Breed("African", "https://images.dog.ceo/breeds/african/n02116738_3024.jpg"),
        Breed("Cairn", "https://images.dog.ceo/breeds/cairn/n02096177_1171.jpg")
    )

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

    @Test
    fun on_swipe_loading_indicator_displayed() {
        rule.launchActivity(Intent())

        val source = Single.just(breeds).delay(1000, TimeUnit.MILLISECONDS)
        whenever(dogsRepository.getRandomBreeds(any(), any())).thenReturn(source)

        assertFalse(rule.activity.activity_main_swipe_layout.isRefreshing)
        onView(withId(R.id.activity_main_swipe_layout)).perform(swipeDown())
        assertTrue(rule.activity.activity_main_swipe_layout.isRefreshing)

        val context = InstrumentationRegistry.getTargetContext()
        onView(withText(context.getString(R.string.activity_main_no_data))).check(matches(isDisplayed()))
    }

    @Test
    fun on_breeds_download_fail_loading_indicator_hide() {
        rule.launchActivity(Intent())

        whenever(dogsRepository.getRandomBreeds(any(), any())).thenReturn(Single.error(Exception()))

        assertFalse(rule.activity.activity_main_swipe_layout.isRefreshing)
        onView(withId(R.id.activity_main_swipe_layout)).perform(swipeDown())
        assertFalse(rule.activity.activity_main_swipe_layout.isRefreshing)

        val context = InstrumentationRegistry.getTargetContext()
        onView(withText(context.getString(R.string.activity_main_no_data))).check(matches(isDisplayed()))
    }

    @Test
    fun on_breeds_download_success_breeds_cards_displayed() {
        rule.launchActivity(Intent())

        val source = Single.just(breeds)
        whenever(dogsRepository.getRandomBreeds(any(), any())).thenReturn(source)

        onView(withId(R.id.activity_main_swipe_layout)).perform(swipeDown())
        onView(withId(R.id.activity_main_empty_label)).check(matches(not(isDisplayed())))

        onView(withId(R.id.activity_main_dogs_list)).check(matches(isDisplayed()))

        assertEquals(3, (rule.activity.activity_main_dogs_list.adapter as DogsAdapter).items.size)
    }
}