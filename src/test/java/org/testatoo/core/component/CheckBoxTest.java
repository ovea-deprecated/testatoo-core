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
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.CheckBox;
import static org.testatoo.core.ComponentType.Radio;
import static org.testatoo.core.matcher.Matchers.checked;

/**
 * @author dev@testatoo.org
 */
public class CheckBoxTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new CheckBox(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a CheckBox but a Radio"));
        }
    }

    @Test
    public void can_check_and_modifiy_checkbox_state() {
        CheckBox checkBox = new CheckBox(evaluator, id);

        when(evaluator.isChecked(checkBox)).thenReturn(false, true, false);
        assertThat(checkBox, is(not(checked())));
        checkBox.check();
        assertThat(checkBox, is(checked()));
        checkBox.unCheck();
        assertThat(checkBox, is(not(checked())));

        verify(evaluator, times(3)).isChecked(checkBox);
        verify(evaluator, times(1)).check(checkBox);
        verify(evaluator, times(1)).unCheck(checkBox);

    }

    @Test
    public void test_toString() {
        CheckBox checkBox = new CheckBox(evaluator, id);

        when(evaluator.isChecked(checkBox)).thenReturn(false);
        when(evaluator.label(checkBox)).thenReturn("label");

        assertThat(checkBox.toString(), is("class org.testatoo.core.component.CheckBox with state : enabled:true, visible:true, label:label, checked:false"));
    }
}
