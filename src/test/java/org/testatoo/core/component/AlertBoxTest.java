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
import static org.testatoo.core.ComponentType.AlertBox;
import static org.testatoo.core.ComponentType.Radio;

public class AlertBoxTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(AlertBox);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);
        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);
        try {
            new AlertBox(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a AlertBox but a Radio"));
        }
    }

    @Test
    public void test_alertbox_message() {
        AlertBox alertBox = new AlertBox(evaluator, id);
        when(evaluator.message(alertBox)).thenReturn("message");
        assertThat(alertBox.message(), is("message"));
    }

    @Test
    public void test_toString() {
        AlertBox alertBox = new AlertBox(evaluator, id);

        when(evaluator.isVisible(alertBox)).thenReturn(true);
        when(evaluator.isEnabled(alertBox)).thenReturn(true);
        when(evaluator.title(alertBox)).thenReturn("title");
        when(evaluator.message(alertBox)).thenReturn("message");

        assertThat(alertBox.toString(), is("class org.testatoo.core.component.AlertBox with state : enabled:true, visible:true, title:title, message:message"));
    }
}
