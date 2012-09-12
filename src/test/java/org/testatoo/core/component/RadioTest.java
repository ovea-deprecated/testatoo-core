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
import static org.testatoo.core.ComponentType.Panel;
import static org.testatoo.core.ComponentType.Radio;

/**
 * @author dev@testatoo.org
 */
public class RadioTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Panel);

        try {
            new Radio(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a Radio but a Panel"));
        }
    }

    @Test
    public void can_check_radiobutton() {
        Radio radio = new Radio(evaluator, id);

        when(evaluator.isChecked(radio)).thenReturn(false, true);

        assertThat(radio.isChecked(), is(false));
        radio.check();
        assertThat(radio.isChecked(), is(true));

        verify(evaluator, times(2)).isChecked(radio);
        verify(evaluator, times(1)).check(radio);
    }

    @Test
    public void test_toString() {
        when(evaluator.label(any(Field.class))).thenReturn("label");
        when(evaluator.isChecked(any(Radio.class))).thenReturn(true);

        Radio radio = new Radio(evaluator, id);
        assertThat(radio.toString(), is("class org.testatoo.core.component.Radio with state : enabled:true, visible:true, label:label, checked:true"));
    }
}
