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

package org.testatoo.core.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.Evaluator;
import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;

import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.Button;

/**
 * @author dev@testatoo.org
 */
public class MouseTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.name()).thenReturn(Evaluator.DEFAULT_NAME);

        // Needed for Mouse ;)
        EvaluatorHolder.register(evaluator);
        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Button);
    }

    @After
    public void clean() {
        EvaluatorHolder.unregister(Evaluator.DEFAULT_NAME);
    }

    @Test
    public void can_click_on_component() {
        Component component = new Component(evaluator, id);
        Mouse.clickOn(component);

        verify(evaluator, times(1)).click(component, Click.left);
    }

    @Test
    public void can_doubleClick_on_component() {
        Component component = new Component(evaluator, id);
        Mouse.doubleClickOn(component);

        verify(evaluator, times(1)).doubleClick(component);
    }

    @Test
    public void can_rightClick_on_component() {
        Component component = new Component(evaluator, id);
        Mouse.rightClickOn(component);

        verify(evaluator, times(1)).click(component, Click.right);
    }

    @Test
    public void can_mouseOver_on_component() {
        Component component = new Component(evaluator, id);
        Mouse.mouseOverOn(component);

        verify(evaluator, times(1)).mouseOver(component);
    }

    @Test
    public void can_mouseOut_on_component() {
        Component component = new Component(evaluator, id);
        Mouse.mouseOutOf(component);

        verify(evaluator, times(1)).mouseOut(component);
    }

    @Test
    public void can_drag_and_component() {
        Component component_1 = new Component(evaluator, id);

        String id_2 = "cmp2";
        when(evaluator.existComponent(id_2)).thenReturn(true);
        when(evaluator.componentType(id_2)).thenReturn(Button);

        Component component_2 = new Component(evaluator, id_2);

        Mouse.drag(component_1).on(component_2);
        verify(evaluator, times(1)).dragAndDrop(component_1, component_2);
    }
}
