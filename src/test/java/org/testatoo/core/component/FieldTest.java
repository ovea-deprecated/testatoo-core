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
import org.testatoo.core.Evaluator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * @author dev@testatoo.org
 */
public class FieldTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
    }

    @Test
    public void can_obtain_value() {
        Field field = new FieldImpl(evaluator, id);

        when(evaluator.value(field)).thenReturn("myValue");
        assertThat(field.value(), is("myValue"));
    }

    @Test
    public void can_obtain_label() {
        Field field = new FieldImpl(evaluator, id);

        when(evaluator.value(field)).thenReturn("myValue");
        when(evaluator.label(field)).thenReturn("myLabel");

        assertThat(field.label(), is("myLabel"));
    }

    @Test
    public void test_toString() {
        Field field = new FieldImpl(evaluator, id);

        when(evaluator.value(field)).thenReturn("value");
        when(evaluator.label(field)).thenReturn("label");

        assertThat(field.toString(), is("class org.testatoo.core.component.FieldImpl with state : enabled:true, visible:true, value:value, label:label"));
    }
}
