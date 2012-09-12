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
import static org.testatoo.core.ComponentType.Radio;
import static org.testatoo.core.ComponentType.TextField;

/**
 * @author dev@testatoo.org
 */
public class TextFieldTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Field.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Field.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(TextField);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new TextField(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a TextField but a Radio"));
        }
    }

    @Test
    public void can_check_max_length_accepted_by_textfield() {
        when(evaluator.value(any(TextField.class))).thenReturn("");
        when(evaluator.label(any(TextField.class))).thenReturn("");
        when(evaluator.maxLength(any(TextField.class))).thenReturn(10);

        TextField textField = new TextField(evaluator, id);
        assertThat(textField.maxLength(), is(10));
    }

    @Test
    public void test_toString() {
        when(evaluator.value(any(TextField.class))).thenReturn("myValue");
        when(evaluator.label(any(TextField.class))).thenReturn("label");
        when(evaluator.maxLength(any(TextField.class))).thenReturn(10);

        TextField textField = new TextField(evaluator, id);
        assertThat(textField.toString(), is("class org.testatoo.core.component.TextField with state : enabled:true, visible:true, value:myValue, label:label, maxLength:10"));
    }
}
