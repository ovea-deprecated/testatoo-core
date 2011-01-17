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
import org.testatoo.core.nature.LabelSupport;


/**
 * This class is a matcher (written based on hamcrest possibilites) to test the label of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class LabelValue extends TypeSafeMatcher<LabelSupport> {

    private String expectedLabel;
    private Boolean checkLabelAvailablility;

    /**
     * Uses the matcher LabelValue to compare the label of a given graphic object to the expected value of the matcher,
     * using the syntax "label()" or "label("xxx")" or "emptyLabel()"
     *
     * @param labelSupport the graphic object with a label
     * @return True if the label matches the expectedLabel of the matcher LabelValue
     */
    @Override
    public boolean matchesSafely(LabelSupport labelSupport) {

        if (checkLabelAvailablility) {
            return labelSupport.label().length() > 0;
        }

        return expectedLabel.equals(labelSupport.label());
    }

    /**
     * To append the description of the label to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("label:" + expectedLabel);
    }

    /**
     * Constructor with given label
     *
     * @param checkLabelAvailablility boolean with True value if we want to know if the label exists
     * @param label                   label we want to know if it's match to the label of the object
     */
    public LabelValue(Boolean checkLabelAvailablility, String label) {
        this.checkLabelAvailablility = checkLabelAvailablility;
        expectedLabel = label;
    }

    /**
     * Using the syntax "emptyLabel()", the test creates a LabelValue matcher
     *
     * @return a new LabelValue matcher
     */
    @Factory
    public static Matcher<LabelSupport> emptyLabel() {
        return new LabelValue(false, "");
    }

    /**
     * Using the syntax "Label("xxxx")", the test creates a LabelValue matcher
     *
     * @param label the label to compare to the label of the graphic object
     * @return a new LabelValue matcher
     */
    @Factory
    public static Matcher<LabelSupport> label(String label) {
        return new LabelValue(false, label);
    }

    /**
     * Using the syntax "Label()", the test creates a LabelValue matcher
     *
     * @return a new LabelValue matcher
     */
    @Factory
    public static Matcher<LabelSupport> label() {
        return new LabelValue(true, null);
    }
}
