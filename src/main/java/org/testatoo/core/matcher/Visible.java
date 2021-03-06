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
 * This class is a matcher (written based on hamcrest possibilites) to test the visibility of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class Visible extends TypeSafeMatcher<Component> {

    /**
     * Uses the matcher Visible to know the visibility of a given component using the syntax "is(visible())" or "is(not(visible()))"
     *
     * @param component the graphic object
     * @return True if the component is visible
     */
    @Override
    public boolean matchesSafely(Component component) {
        return component.isVisible();
    }

    /**
     * To append the description of the visibility to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("visible:true");
    }

    /**
     * Using the syntax "visible()", the test creates a Visible matcher
     *
     * @return a new Visible matcher
     */
    public static Visible visible() {
        return new Visible();
    }
}
