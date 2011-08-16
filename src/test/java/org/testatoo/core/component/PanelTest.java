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

import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.ComponentException;
import org.testatoo.core.Evaluator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.Panel;
import static org.testatoo.core.ComponentType.Radio;

public class PanelTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Panel);
        when(evaluator.title(any(Panel.class))).thenReturn("PanelTitle");
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new Panel(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a Panel but a Radio"));
        }
    }

    @Test
    public void test_panel_as_container() {
        when(evaluator.existComponent("myId2")).thenReturn(true);
        when(evaluator.componentType("myId2")).thenReturn(Panel);

        Panel panel_1 = new Panel(evaluator, id);
        Panel panel_2 = new Panel(evaluator, "myId2");

        when(evaluator.contains(panel_1, panel_2)).thenReturn(true);
        when(evaluator.contains(panel_2, panel_1)).thenReturn(false);

        assertThat(panel_1.contains(panel_2), is(true));
        assertThat(panel_2.contains(panel_1), is(false));
    }

    @Test
    public void test_toString() {
        Panel panel = new Panel(evaluator, id);
        assertThat(panel.toString(), is("class org.testatoo.core.component.Panel with state : enabled:true, visible:true, title:PanelTitle"));
    }
}
