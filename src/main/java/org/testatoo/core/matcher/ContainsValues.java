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
import org.testatoo.core.component.ListModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the content of lists.
 *
 * @author dev@testatoo.org
 */
public final class ContainsValues extends TypeSafeMatcher<ListModel> {

    private List<String> expectedValues = new ArrayList<String>();
    private Boolean exactly = TRUE;

    /**
     * Uses the matcher ContainsValues to compare the content of a given listModel to the expected values of the matcher,
     * using the syntax "containsValues("xxx", "yyyy", "zzz")" or "containsExactlyValues("xxx", "yyyy", "zzz")"
     *
     * @param listModel the listModel
     * @return True if the list contains (contains exactly for second syntax) the values of the matcher ContainsValues
     */
    public boolean matchesSafely(ListModel listModel) {
        if (exactly) {
            return listModel.values().containsAll(expectedValues) && listModel.values().size() == expectedValues.size();
        }

        return listModel.values().containsAll(expectedValues);
    }

    /**
     * To append the description of the content of the list to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        String exactlyString = exactly ? " exactly" : "";
        description.appendText("contains" + exactlyString + " value(s):").appendText(Arrays.deepToString(expectedValues.toArray()));
    }

    /**
     * Constructor with given values
     *
     * @param exactly boolean with True value if we want to know if the list contains exactly the values
     * @param values  values we want to know if they are (or are exactly) in the list
     */
    public ContainsValues(Boolean exactly, String... values) {
        if (exactly != null) {
            this.exactly = exactly;
        }
        expectedValues.addAll(Arrays.asList(values));
    }

    /**
     * Using the syntax "containsValues("xxx", "yyyy", "zzz")", the test creates a ContainsValues matcher
     *
     * @param values the list of values to compare to the content of the list
     * @return a new ContainsValues matcher
     */
    @Factory
    public static Matcher<ListModel> contains(String... values) {
        return new ContainsValues(false, values);
    }

    /**
     * Using the syntax "containsExactlyValues("xxx", "yyyy", "zzz")", the test creates a ContainsValues matcher
     *
     * @param values the list of values to compare to the content of the list
     * @return a new ContainsValues matcher
     */
    @Factory
    public static Matcher<ListModel> containsExaclty(String... values) {
        return new ContainsValues(true, values);
    }
}
