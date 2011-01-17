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
import org.testatoo.core.nature.MultiSelectable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the selected values in a "multi-selection" list.
 *
 * @author dev@testatoo.org
 */
public final class SelectedValues extends TypeSafeMatcher<MultiSelectable> {

    private List<String> expectedSelectedValues = new ArrayList<String>();

    /**
     * Uses the matcher SelectedValues to compare the selected values in a given list to the expected values of the matcher,
     * using the syntax "selectedValues("xxx","yyy", "zzz")"
     *
     * @param multiSelectable the "multi-selection" list
     * @return True if the selected values in the list are equals to the values of the matcher SelectedValues
     */
    @Override
    public boolean matchesSafely(MultiSelectable multiSelectable) {
        return !multiSelectable.selectedValues().isEmpty()
                && multiSelectable.selectedValues().containsAll(expectedSelectedValues);
    }

    /**
     * To append the description of the selected values of the list to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("selected values:" + Arrays.deepToString(expectedSelectedValues.toArray()));
    }

    /**
     * Constructor with given value
     *
     * @param values values we want to know if they are the selected values of the list
     */
    public SelectedValues(String... values) {
        expectedSelectedValues.addAll(Arrays.asList(values));
    }

    /**
     * Using the syntax "selectedValues("xxx", "yyy", "zzz")", the test creates a SelectedValues matcher
     *
     * @param values the values to compare to the selected values
     * @return a new SelectedValues matcher
     */
    @Factory
    public static Matcher<MultiSelectable> selectedValues(String... values) {
        return new SelectedValues(values);
    }
}

