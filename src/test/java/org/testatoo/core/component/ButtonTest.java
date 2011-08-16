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
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.Button;
import static org.testatoo.core.ComponentType.Radio;

public class ButtonTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Button);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new Button(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a Button but a Radio"));
        }
    }

    @Test
    public void test_button_text() {
        Button button = new Button(evaluator, id);
        when(evaluator.text(button)).thenReturn("buttonText");
        assertThat(button.text(), is("buttonText"));
    }

    @Test
    public void test_button_icon() {
        Button button = new Button(evaluator, id);
        when(evaluator.icon(button)).thenReturn("http://localhost/enter.gif");
        assertThat(button.icon(), is("http://localhost/enter.gif"));
    }

    @Test
    public void test_toString() {
        Button button = new Button(evaluator, id);
        when(evaluator.isVisible(button)).thenReturn(true);
        when(evaluator.isEnabled(button)).thenReturn(true);
        when(evaluator.icon(button)).thenReturn("http://localhost/enter.gif");
        when(evaluator.text(button)).thenReturn("ButtonText");

        assertThat(button.toString(), is("class org.testatoo.core.component.Button with state : enabled:true, visible:true, text:ButtonText, icon:http://localhost/enter.gif"));
    }
}
