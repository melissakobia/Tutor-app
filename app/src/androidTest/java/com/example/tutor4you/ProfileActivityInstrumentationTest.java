package com.example.tutor4you;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class ProfileActivityInstrumentationTest {

    @Rule

    public ActivityTestRule<ProfileActivity> activityTestRule =
            new ActivityTestRule<>(ProfileActivity.class);

    @Test
    public void validateEditTextName() {
        onView(withId(R.id.nameEditText)).perform(typeText("John Doe"))
                .check(matches(withText("John Doe")));
    }

    @Test
    public void validateEditTextAge() {
        onView(withId(R.id.ageEditText)).perform(typeText("30"))
                .check(matches(withText("30")));
    }

    @Test
    public void validateEditTextGender() {
        onView(withId(R.id.genderEditText)).perform(typeText("Male"))
                .check(matches(withText("Male")));
    }

    @Test
    public void validateEditTextSpecialization() {
        onView(withId(R.id.specializationEditText)).perform(typeText("Chemistry"))
                .check(matches(withText("Chemistry")));
    }

    @Test
    public void validateEditTextEducationLevel() {
        onView(withId(R.id.educationLevelEditText)).perform(typeText("masters"))
                .check(matches(withText("masters")));
    }

    @Test
    public void validateEditTextRate() {
        onView(withId(R.id.nameEditText)).perform(typeText("1000"))
                .check(matches(withText("1000")));
    }


//    @Test
//    public void nameIsSentToViewProfileActivity() {
//        String name = "John Doe";
//        onView(withId(R.id.nameEditText)).perform(typeText(name)).perform(closeSoftKeyboard());
//        try {                             // the sleep method requires to be checked and handled so we use try block
//            Thread.sleep(250);
//        } catch (InterruptedException e){
//            System.out.println("got interrupted!");
//        }
//        onView(withId(R.id.createProfileButton)).perform(click());
//        onView(withId(R.id.textViewTutorName)).check(matches
//                (withText("Here are all the restaurants near: " + name)));
//    }
}
