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

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * This class is a matcher (written based on hamcrest possibilites) that is used to decorate
 * another Matcher, retaining the behavior but allowing tests to be more expressive
 *
 * @param <T> an other matcher
 * @author dev@testatoo.org
 */
public final class Has<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    /**
     * Uses the matcher Has to have a more natural syntax like "has(maxLength(25))" or "has(label())"
     *
     * @param obj the object we want to test
     * @return the result of matching with the delegated matcher
     */
    public boolean matches(Object obj) {
        return matcher.matches(obj);
    }

    /**
     * To append the description of the Has matcher (and the description of the decorated matcher) to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("has ").appendDescriptionOf(matcher);
    }

    /**
     * Constructor with a given matcher
     *
     * @param matcher the decorated matcher
     */
    public Has(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    /**
     * Using the syntax "has(xxxxxx)", the test creates a Has matcher
     *
     * @param <T>     type of tested object
     * @param matcher the decorated matcher
     * @return a new Has matcher
     */
    @Factory
    public static <T> Matcher<T> has(Matcher<T> matcher) {
        return new Has<T>(matcher);
    }
}
