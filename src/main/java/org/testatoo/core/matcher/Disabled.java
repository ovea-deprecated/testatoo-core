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
import org.testatoo.core.component.Component;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the state disabled of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class Disabled extends TypeSafeMatcher<Component> {

    /**
     * Uses the matcher Disabled to know if a given component is disabled using the syntax "is(disabled())" or "is(not(disabled()))"
     *
     * @param component the graphic object
     * @return True if the component is disabled
     */
    @Override
    public boolean matchesSafely(Component component) {
        return component.isDisabled();
    }

    /**
     * To append the description of the disabled state to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("enabled:false");
    }

    /**
     * Using the syntax "disabled()", the test creates a Disabled matcher
     *
     * @return a new Disabled matcher
     */
    @Factory
    public static Disabled disabled() {
        return new Disabled();
    }
}
