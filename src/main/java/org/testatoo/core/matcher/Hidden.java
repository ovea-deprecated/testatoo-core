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
