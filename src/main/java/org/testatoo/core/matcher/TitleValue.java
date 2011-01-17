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
import org.testatoo.core.nature.TitleSupport;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the title of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class TitleValue extends TypeSafeMatcher<TitleSupport> {

    private String expectedTitle;

    /**
     * Uses the matcher TitleValue to compare the title of an object to the expected title of the matcher,
     * using the syntax "title("xxx")"
     *
     * @param titleSupport the graphic object that supports title
     * @return True if the title on the object is equal to the title of the matcher TitleValue
     */
    @Override
    public boolean matchesSafely(TitleSupport titleSupport) {
        return titleSupport.title().equals(expectedTitle);
    }

    /**
     * To append the description of the title to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("title:" + expectedTitle);
    }

    /**
     * Constructor with given value
     *
     * @param title title we want to know if it's the title of the object
     */
    public TitleValue(String title) {
        expectedTitle = title;
    }

    /**
     * Using the syntax "title("xxx")", the test creates a TitleValue matcher
     *
     * @param title the title to compare to the title of the graphic object
     * @return a new TitleValue matcher
     */
    @Factory
    public static TitleValue value(String title) {
        return new TitleValue(title);
    }
}
