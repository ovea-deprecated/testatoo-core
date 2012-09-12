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
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.component.Component;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the hidden state of a graphic object.
 *
 * @author dev@testatoo.org
 */
public class Hidden extends TypeSafeMatcher<Component> {
    /**
     * Uses the matcher Hidden to know the hidden state of a given component using the syntax "is(hidden())" or "is(not(hidden()))"
     *
     * @param component the graphic object
     * @return True if the component is hidden
     */
    @Override
    public boolean matchesSafely(Component component) {
        return !component.isVisible();
    }

    /**
     * To append the description of the hidden state to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("hidden:true");
    }

    /**
     * Using the syntax "hidden()", the test creates a Hidden state matcher
     *
     * @return a new Hidden matcher
     */
    public static Hidden hidden() {
        return new Hidden();
    }
}
