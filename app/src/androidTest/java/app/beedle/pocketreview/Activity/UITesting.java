package app.beedle.pocketreview.Activity;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.beedle.pocketreview.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UITesting {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ClickPocketNote() {
        onView( //Click PocketNote Icon.
                allOf(withId(R.id.pocketIcon), isDisplayed())
        ).perform(click());

        onView(
                allOf(withId(R.id.drawer_pocket_tab), isDisplayed())
        );

    }

    @Test
    public void ClickCheckCurrency() {
        onView( //Click Currency Icon then show loading.
                allOf(withId(R.id.currencyIcon), isDisplayed())
        ).perform(click());

        onView(
                allOf(withId(R.id.tvLoading), isDisplayed())
        );

    }

    @Test
    public void ClickCheckLocation() {
        onView( //Click Location Icon then show loading.
                allOf(withId(R.id.locationIcon), isDisplayed())
        ).perform(click());

        onView(
                allOf(withId(R.id.tvLocation), isDisplayed())
        );

    }

    @Test
    public void ClickMenuPocketNote() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.tbMain),
                                        childAtPosition(
                                                withId(R.id.menuIcon),
                                                5)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.navigationView),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(
                allOf(withId(R.id.drawer_pocket_tab), isDisplayed())
        );

    }

    @Test
    public void ClickMenuCurrency() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.tbMain),
                                        childAtPosition(
                                                withId(R.id.menuIcon),
                                                5)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.navigationView),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(
                allOf(withId(R.id.currencyTabMenu), isDisplayed())
        );

    }

    @Test
    public void AddNote() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.pocketIcon),
                        childAtPosition(
                                allOf(withId(R.id.menuIcon),
                                        childAtPosition(
                                                withId(R.id.drawer),
                                                1)),
                                6),
                        isDisplayed()));
        appCompatImageView.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Add Note"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction materialSpinner = onView(
                allOf(withId(R.id.spinner), withText("AUD"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.constraint.ConstraintLayout")),
                                        7),
                                0),
                        isDisplayed()));
        materialSpinner.perform(click());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.spinner),
                        childAtPosition(
                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                0)))
                .atPosition(26);
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.doneBtn), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_AddNote),
                                        1),
                                6),
                        isDisplayed()));
        appCompatButton.perform(click());

    }

    @Test
    public void ClickMenuLocation() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.tbMain),
                                        childAtPosition(
                                                withId(R.id.menuIcon),
                                                5)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.navigationView),
                                        0)),
                        4),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(
                allOf(withId(R.id.locationMenu), isDisplayed())
        );

    }

    @Test
    public void AddNoteDeleteShare() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.currencyIcon),
                        childAtPosition(
                                allOf(withId(R.id.menuIcon),
                                        childAtPosition(
                                                withId(R.id.drawer),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.tbAddNote),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.pocketIcon),
                        childAtPosition(
                                allOf(withId(R.id.menuIcon),
                                        childAtPosition(
                                                withId(R.id.drawer),
                                                1)),
                                6),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.tbPocket),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.navigationView),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Add Note"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.doneBtn), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_AddNote),
                                        1),
                                6),
                        isDisplayed()));
        appCompatButton.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Add Note"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.doneBtn), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_AddNote),
                                        1),
                                6),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.valueEditNote), withText("15\n15"),
                        childAtPosition(
                                allOf(withId(R.id.contentDetail),
                                        childAtPosition(
                                                withId(R.id.test),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("15..\n15"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.valueEditNote), withText("15..\n15"),
                        childAtPosition(
                                allOf(withId(R.id.contentDetail),
                                        childAtPosition(
                                                withId(R.id.test),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.doneBtnEditNote), withText("Done"),
                        childAtPosition(
                                allOf(withId(R.id.test),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.valueEditNote), withText("15..\n15"),
                        childAtPosition(
                                allOf(withId(R.id.contentDetail),
                                        childAtPosition(
                                                withId(R.id.test),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("1\n15"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.valueEditNote), withText("1\n15"),
                        childAtPosition(
                                allOf(withId(R.id.contentDetail),
                                        childAtPosition(
                                                withId(R.id.test),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.doneBtnEditNote), withText("Done"),
                        childAtPosition(
                                allOf(withId(R.id.test),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.deleteNote),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.pocketIcon),
                        childAtPosition(
                                allOf(withId(R.id.menuIcon),
                                        childAtPosition(
                                                withId(R.id.drawer),
                                                1)),
                                6),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
