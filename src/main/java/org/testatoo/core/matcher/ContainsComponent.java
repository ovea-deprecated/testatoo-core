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
import org.testatoo.core.ListSelection;
import org.testatoo.core.Selection;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.Container;

import java.util.ArrayList;
import java.util.List;

public class ContainsComponent<T> extends TypeSafeMatcher<Component> {

    private List<Component> selection;
    private Boolean notContainer = false;

    public boolean matchesSafely(Component component) {

        try {
            Container container = (Container) component;
            return container.contains(selection.toArray(new Component[selection.size()]));
        } catch (ClassCastException e) {
            notContainer = true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        if (notContainer) {
            description.appendText("a container");
            return;
        }

        description.appendText("contain one of ");
        description.appendValueList("{", ", ", "}", selection);

    }

    public ContainsComponent(Selection<? extends Component> selection) {
        this.selection = new ArrayList<Component>();
        for (Component component : selection) {
            this.selection.add(component);
        }
    }

    @Factory
    public static Matcher<Component> contains(Selection<? extends Component> components) {
        return new ContainsComponent<Component>(components);
    }

    @Factory
    public static Matcher<Component> contains(Component... components) {
        return new ContainsComponent<Component>(ListSelection.of(components));
    }


}

