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
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.nature.Checkable;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the state "unchecked" of a checkable object.
 *
 * @author dev@testatoo.org
 */
public final class UnChecked extends TypeSafeMatcher<Checkable> {

    /**
     * Uses the matcher UnChecked to test the state (checked or not) of a checkable object using the syntax
     * "is(unChecked())" or *is(not(unChecked()))"
     *
     * @param checkable the checkable object
     * @return True if the checkable object is not checked
     */
    @Override
    public boolean matchesSafely(Checkable checkable) {
        return !checkable.isChecked();
    }

    /**
     * To append the description of the unchecked state to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("checked:false");
    }


    /**
     * Using the syntax "unChecked()", the test creates a UnChecked matcher
     *
     * @return a new UnChecked matcher
     */
    @Factory
    public static UnChecked unChecked() {
        return new UnChecked();
    }

}
