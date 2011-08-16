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
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.ComponentException;
import org.testatoo.core.Evaluator;
import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the presence of a graphic object.
 *
 * @author dev@testatoo.org
 */
public class Exist extends TypeSafeMatcher<Component> {

    /**
     * Uses the matcher Exist to know the presence of a given component using the syntax "exist()" or "not(exist())"
     *
     * @param component the graphic object
     * @return True if the component exist
     */
    @Override
    protected boolean matchesSafely(Component component) {
        try {
            new Component(EvaluatorHolder.<Evaluator>get(), component.id());
            return true;
        } catch (ComponentException e) {
            return false;
        }
    }

    /**
     * To append the description of the presence to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("exist:true");
    }

    /**
     * Using the syntax "exist()", the test creates a Exist matcher
     *
     * @return a new Exist matcher
     */
    public static Matcher<Component> exist() {
        return new Exist();
    }
}
