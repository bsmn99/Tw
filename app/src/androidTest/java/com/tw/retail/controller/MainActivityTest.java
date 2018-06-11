package com.tw.retail.controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tw.retail.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textView), withText("Electronics"),
                      childAtPosition(childAtPosition(withId(R.id.categoryListRecyclerView), 0), 0),
                      isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.textView), withText("Microwave oven"),
                      childAtPosition(childAtPosition(withId(R.id.subCategoryContainer), 0), 0),
                      isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.addToCart), withText("Add to cart"),
                      childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1),
                      isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.checkOut), withText("Checkout"),
                                                        childAtPosition(childAtPosition(
                                                                withId(android.R.id.content), 0),
                                                                        2), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(allOf(withContentDescription("Navigate up"),
                                                            childAtPosition(
                                                                    allOf(withId(R.id.action_bar),
                                                                          childAtPosition(
                                                                                  withId(R.id.action_bar_container),
                                                                                  0)), 1),
                                                            isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(allOf(withContentDescription("Navigate up"),
                                                             childAtPosition(
                                                                     allOf(withId(R.id.action_bar),
                                                                           childAtPosition(
                                                                                   withId(R.id.action_bar_container),
                                                                                   0)), 1),
                                                             isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.textView), withText("Furniture"),
                      childAtPosition(childAtPosition(withId(R.id.categoryListRecyclerView), 1), 0),
                      isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatTextView4 = onView(allOf(withId(R.id.textView), withText("Table"),
                                                          childAtPosition(childAtPosition(
                                                                  withId(R.id.subCategoryContainer),
                                                                  0), 0), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addToCart), withText("Add to cart"),
                      childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1),
                      isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.button1), childAtPosition(
                allOf(withId(R.id.cartLayout), childAtPosition(withId(R.id.badge_layout1), 0)), 0),
                                                        isDisplayed()));
        appCompatButton4.perform(click());

        Matcher<View> secondIconMatcher = allOf(withId(R.id.removeFromCart));
        onView(withIndex(secondIconMatcher , 1)).perform(click());

        ViewInteraction linearLayout = onView(childAtPosition(allOf(withId(R.id.cartContainer),
                                                                    childAtPosition(withClassName(
                                                                            is("android.widget.ScrollView")),
                                                                                    0)), 0));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.checkOut), withText("Checkout"),
                                                        childAtPosition(childAtPosition(
                                                                withId(android.R.id.content), 0),
                                                                        2), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(allOf(withId(R.id.checkOut), withText("Checkout"),
                                                        childAtPosition(
                                                                allOf(withId(R.id.cartContainer),
                                                                      childAtPosition(withClassName(
                                                                              is("android.widget.ScrollView")),
                                                                                      0)), 1)));
        appCompatButton6.perform(scrollTo(), click());

    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
                                                 final int position) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
                        ((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}
