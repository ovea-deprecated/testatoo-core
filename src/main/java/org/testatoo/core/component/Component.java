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

package org.testatoo.core.component;

import org.testatoo.core.ComponentException;
import org.testatoo.core.ComponentType;
import org.testatoo.core.Evaluator;

import static org.testatoo.core.ComponentType.Undefined;

/**
 * This is the base class for all type of graphic components.
 *
 * @author dev@testatoo.org
 */
public class Component {

    /**
     * The unique identifier of the component
     */
    private String id;
    /**
     * The evaluator is dependant of the choosen technology
     */
    protected Evaluator<?> evaluator;

    /**
     * Class constructor specifying the evaluator to use and the id of the component we want to test.
     * The constructor checks if the given id does correspond to a graphic component.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the component
     * @throws ComponentException if the given id does not correspond to a component
     */
    public Component(Evaluator evaluator, String id) {
        if (evaluator == null) {
            throw new ComponentException("An evaluator must be defined");
        }
        this.evaluator = evaluator;

        if (!evaluator.existComponent(id)) {
            throw new ComponentException("Cannot find component defined by id=" + id);
        }
        this.id = id;
    }

    /**
     * To get the id of the component
     *
     * @return the id of the component
     */
    public String id() {
        return id;
    }

    /**
     * To know if the focus is on the component
     *
     * @return True if component has focus
     */
    public Boolean hasFocus() {
        return evaluator.hasFocus(this);
    }

    /**
     * To know if the component is visible
     *
     * @return True if the component is visible
     */
    public Boolean isVisible() {
        return evaluator.isVisible(this);
    }

    /**
     * To know if the component is enabled
     *
     * @return True is the component is enabled
     */
    public Boolean isEnabled() {
        return evaluator.isEnabled(this);
    }

    /**
     * To know if the component is disabled
     *
     * @return True if the component is disabled
     */
    public Boolean isDisabled() {
        return !isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;
        return id.equals(component.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * To get the string describing the component
     *
     * @return the string describing the component
     */
    @Override
    public String toString() {
        return this.getClass().toString() + " with state : enabled:" + isEnabled() + ", visible:" + isVisible();
    }

    /**
     * Throws exception if the component has not the expected type
     *
     * @param type the expected type for the component
     * @throws ComponentException if the current component has not the given type
     */
    protected void checkIsExpectedComponent(ComponentType type) {
        if (evaluator.componentType(id) != type && evaluator.componentType(id) != Undefined) {
            throw new ComponentException("The component with id=" + id + " is not a " + type + " but a " + evaluator.componentType(id));
        }
    }
}
