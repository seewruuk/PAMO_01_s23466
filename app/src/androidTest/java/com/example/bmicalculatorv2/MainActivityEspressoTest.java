package com.example.bmicalculatorv2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCalculateAndDisplayResults_withValidInputs() {
        // Wpisanie danych
        onView(withId(R.id.editTextMasa)).perform(typeText("70"), closeSoftKeyboard());
        onView(withId(R.id.editTextWzrost)).perform(typeText("1.75"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(typeText("25"), closeSoftKeyboard());

        // Wybór opcji ze spinnera
        onView(withId(R.id.spinnerActivity)).perform(click());
        onView(withText("2 do 3 razy w tygodniu")).perform(click());

        // Kliknięcie przycisku
        onView(withId(R.id.buttonOblicz)).perform(click());

        // Sprawdzenie wyników
        onView(withId(R.id.textViewBMIResult)).check(matches(withSubstring("BMI:")));
        onView(withId(R.id.textViewCalorieResult)).check(matches(withSubstring("Dzienne zapotrzebowanie")));
        onView(withId(R.id.textViewRecipes)).check(matches(not(withText(""))));
    }
}
