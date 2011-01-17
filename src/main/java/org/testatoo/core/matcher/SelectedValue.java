/**
 * Copyright (C) 2008 Ovea <dev@testatoo.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.testatoo.core.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.nature.SimpleSelectable;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the selected value in a "single-selection" list.
 *
 * @author dev@testatoo.org
 */
public final class SelectedValue extends TypeSafeMatcher<SimpleSelectable> {

    private String expectedSelectedValue;

    /**
     * Uses the matcher SelectedValue to compare the selected value in a given list to the expected value of the matcher,
     * using the syntax "selectedValue("xxx")"
     *
     * @param simpleSelectable the "single-selection" list
     * @return True if the selected value in the list is equal to the value of the matcher SelectedValue
     */
    @Override
    public boolean matchesSafely(SimpleSelectable simpleSelectable) {
        return expectedSelectedValue.equals(simpleSelectable.selectedValue());
    }

    /**
     * To append the description of the selected value of the list to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("selected value:" + expectedSelectedValue);
    }

    /**
     * Constructor with given value
     *
     * @param value value we want to know if it's the selected value of the list
     */
    public SelectedValue(String value) {
        expectedSelectedValue = value;
    }

    /**
     * Using the syntax "selectedValue("xxx")", the test creates a SelectedValue matcher
     *
     * @param value the value to compare to the selected value
     * @return a new SelectedValue matcher
     */
    @Factory
    public static Matcher<SimpleSelectable> selectedValue(String value) {
        return new SelectedValue(value);
    }
}