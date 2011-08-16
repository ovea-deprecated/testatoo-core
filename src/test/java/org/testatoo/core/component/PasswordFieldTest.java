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
import static org.testatoo.core.ComponentType.PasswordField;
import static org.testatoo.core.ComponentType.Radio;

public class PasswordFieldTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Field.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Field.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(PasswordField);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new PasswordField(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a PasswordField but a Radio"));
        }
    }

    @Test
    public void can_check_max_length_accepted_by_passwordfield() {
        when(evaluator.value(any(Field.class))).thenReturn("");
        when(evaluator.label(any(PasswordField.class))).thenReturn("");
        when(evaluator.maxLength(any(PasswordField.class))).thenReturn(10);

        PasswordField passwordField = new PasswordField(evaluator, id);
        assertThat(passwordField.maxLength(), is(10));
    }

    @Test
    public void test_toString() {
        when(evaluator.value(any(PasswordField.class))).thenReturn("myPassword");
        when(evaluator.label(any(PasswordField.class))).thenReturn("label");
        when(evaluator.maxLength(any(PasswordField.class))).thenReturn(10);

        PasswordField passwordField = new PasswordField(evaluator, id);
        assertThat(passwordField.toString(), is("class org.testatoo.core.component.PasswordField with state : enabled:true, visible:true, value:myPassword, label:label, maxLength:10"));
    }
}
