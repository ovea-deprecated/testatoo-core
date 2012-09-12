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
import org.testatoo.core.component.Component;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the state enabled of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class Enabled extends TypeSafeMatcher<Component> {

    /**
     * Uses the matcher Enabled to know if a given component is enabled using the syntax "is(enabled())" or "is(not(enabled())"
     *
     * @param component the graphic object
     * @return True if the component is enabled
     */
    @Override
    public boolean matchesSafely(Component component) {
        return component.isEnabled();
    }

    /**
     * To append the description of the enabled state to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("enabled:true");
    }

    /**
     * Using the syntax "enabled()", the test creates a Enabled matcher
     *
     * @return a new Enabled matcher
     */
    @Factory
    public static Matcher<Component> enabled() {
        return new Enabled();
    }
}
