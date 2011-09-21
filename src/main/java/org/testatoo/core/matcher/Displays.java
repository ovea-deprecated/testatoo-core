package org.testatoo.core.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.Container;

import java.util.ArrayList;
import java.util.List;

public class Displays extends TypeSafeMatcher<Container> {

    private Component[] components;

    private List<Component> notExistComponents = new ArrayList<Component>();
    private List<Component> notVisibleComponents = new ArrayList<Component>();

    private Displays(Component... components) {
        this.components = components;
    }

    @Override
    protected boolean matchesSafely(Container container) {

        for (Component component : components) {
            if (container.contains(component)) {
                if (!component.isVisible())
                    notVisibleComponents.add(component);
            } else
                notExistComponents.add(component);
        }

        if (notExistComponents.isEmpty() && notVisibleComponents.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public void describeTo(Description description) {
        if (!notExistComponents.isEmpty()) {
            description.appendText("contains all of ");
            description.appendValueList("{", ", ", "}", notExistComponents);
        }
        if (!notExistComponents.isEmpty() && !notVisibleComponents.isEmpty()) {
            description.appendText(" and ");
        }
        if (!notVisibleComponents.isEmpty()) {
            description.appendText("all of this must be visible ");
            description.appendValueList("{", ", ", "}", notVisibleComponents);
        }
    }

    public static Displays displays(Component... components) {
        return new Displays(components);
    }
}
