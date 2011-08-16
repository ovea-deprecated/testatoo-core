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

import org.junit.*;
import org.testatoo.core.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.DialogBox;
import static org.testatoo.core.ComponentType.Radio;

public class DialogBoxTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(DialogBox);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new DialogBox(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a DialogBox but a Radio"));
        }
    }

    @Test
    public void test_dialogbox_message() {
        DialogBox dialogBox = new DialogBox(evaluator, id);
        when(evaluator.message(dialogBox)).thenReturn("message");

        assertThat(dialogBox.message(), is("message"));
    }

    @Test
    public void can_get_buttons() {
        DialogBox dialogBox = new DialogBox(evaluator, id);

        Selection<Button> buttons = ListSelection.of(null, null, null);

        when(evaluator.buttons(dialogBox)).thenReturn(buttons);
        assertThat(dialogBox.buttons().size(), is(3));
    }

    @Test
    public void test_toString() {
        DialogBox dialogBox = new DialogBox(evaluator, id);

        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.title(dialogBox)).thenReturn("title");
        when(evaluator.message(dialogBox)).thenReturn("message");

        assertThat(dialogBox.toString(), is("class org.testatoo.core.component.DialogBox with state : enabled:true, visible:true, title:title, message:message"));
    }
}
