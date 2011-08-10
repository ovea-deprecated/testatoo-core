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
import org.testatoo.core.nature.TextSupport;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the expected text of a graphic object.
 *
 * @author dev@testatoo.org
 */
public final class TextValue extends TypeSafeMatcher<TextSupport> {

    private String expectedText;

    /**
     * Uses the matcher TextValue to compare the text on an object to the expected text of the matcher,
     * using the syntax "text("xxx")"
     *
     * @param textSupport the graphic object that supports text
     * @return True if the text on the button is equal to the text of the matcher TextValue
     */
    @Override
    public boolean matchesSafely(TextSupport textSupport) {
        return textSupport.text().equals(expectedText);
    }

    /**
     * To append the description of the text to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("text:" + expectedText);
    }

    /**
     * Constructor with given value
     *
     * @param expectedText text we want to know if it's the text on the object
     */
    public TextValue(String expectedText) {
        this.expectedText = expectedText;
    }


    /**
     * Using the syntax "text("xxx")", the test creates a TextValue matcher
     *
     * @param text the text to compare to the text of the graphic object
     * @return a new TextValue matcher
     */
    @Factory
    public static Matcher<TextSupport> text(String text) {
        return new TextValue(text);
    }

}
