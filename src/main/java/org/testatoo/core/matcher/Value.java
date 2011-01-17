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
import org.testatoo.core.nature.ValueSupport;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the value of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class Value extends TypeSafeMatcher<ValueSupport> {

    private String value;

    /**
     * Uses the matcher Value to compare the value of an object to the expected value of the matcher,
     * using the syntax "value("xxx")"
     *
     * @param valueSupport the graphic object that supports value
     * @return True if the value is equal to the value of the matcher Value
     */
    @Override
    public boolean matchesSafely(ValueSupport valueSupport) {
        return valueSupport.value().equals(value);
    }

    /**
     * To append the description of the value to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("value:" + value);
    }

    /**
     * Constructor with given value
     *
     * @param value value we want to compare with the value of the object
     */
    public Value(String value) {
        this.value = value;
    }

    /**
     * Using the syntax "value("xxx")", the test creates a Value matcher
     *
     * @param value the value to compare to the value of the object
     * @return a new Value matcher
     */
    @Factory
    public static Matcher<ValueSupport> value(String value) {
        return new Value(value);
    }
}
