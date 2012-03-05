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

import java.util.regex.Pattern;

import static java.lang.Boolean.TRUE;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the content of textsupport.
 *
 * @author dev@testatoo.org
 */
public class ContainsText extends TypeSafeMatcher<TextSupport> {
    private Boolean exactly = TRUE;
    private String text = "";

    /**
     * Uses the matcher ContainsText to compare the content of a given textsupport to the expected values of the matcher,
     * using the syntax "containsText("xxx")" or "containsExactlyText("xxx")"
     *
     * @param textSupport the textSupport
     * @return True if the textsupport contains (contains exactly for second syntax) the values of the matcher ContainsText
     */
    public boolean matchesSafely(TextSupport textSupport) {
        if (exactly) {
            return text.equals(textSupport.text());
        }
        String patternStr = text;
        Pattern pattern = Pattern.compile(patternStr);
        java.util.regex.Matcher matcher = pattern.matcher(textSupport.text());
        return matcher.find();
    }

    /**
     * To append the description of the content of the list to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        String exactlyString = exactly ? " exactly" : "";
        description.appendText("contains" + exactlyString + " text:").appendText(text);
    }

    /**
     * Constructor with given values
     *
     * @param exactly boolean with True value if we want to know if the list contains exactly the values
     * @param text    text we want to know if they are (or are exactly) in the textsupport
     */
    public ContainsText(Boolean exactly, String text) {
        if (exactly != null) {
            this.exactly = exactly;
        }
        this.text = text;
    }

    /**
     * Using the syntax "containsText("xxx")", the test creates a ContainsText matcher
     *
     * @param text the text to compare to the content of the textsupport
     * @return a new ContainsText matcher
     */
    @Factory
    public static Matcher<TextSupport> contains(String text) {
        return new ContainsText(false, text);
    }

    /**
     * Using the syntax "containsExactlyText("xxx")", the test creates a ContainsText matcher
     *
     * @param text the text to compare to the content of the textsupport
     * @return a new ContainsText matcher
     */
    @Factory
    public static Matcher<TextSupport> containsExaclty(String text) {
        return new ContainsText(true, text);
    }
}
