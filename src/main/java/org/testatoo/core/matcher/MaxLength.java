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
import org.testatoo.core.component.AbstractTextField;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the maximum length of a textField.
 *
 * @author dev@testatoo.org
 */
public final class MaxLength extends TypeSafeMatcher<AbstractTextField> {

    private int maxLength;

    /**
     * Uses the matcher MaxLength to test the maximum length of a textField
     *
     * @param abstractTextField the abstractTextField
     * @return True if the maximum length of the abstractTextField is equal to the maxLength of the matcher
     */
    @Override
    public boolean matchesSafely(AbstractTextField abstractTextField) {
        return abstractTextField.maxLength() == maxLength;
    }

    /**
     * To append the description of the maximum length to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("maxLength:" + maxLength);
    }

    /**
     * Constructor with a given maximum length
     *
     * @param maxLength the maximum length of the CellSize to create
     */
    public MaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * To test the maximum length of the textField using the syntax "maxLength(n)"
     * Using this syntax, the test creates a MaxLength matcher  with a maxLength equal to n
     *
     * @param maxLength the maxLength we want to compare with the maximum length of the textField
     * @return a new MaxLength matcher created with the given maximum length
     */
    @Factory
    public static Matcher<AbstractTextField> maxLength(int maxLength) {
        return new MaxLength(maxLength);
    }
}

