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
import org.testatoo.core.ListSelection;
import org.testatoo.core.Selection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testatoo.core.ComponentType.Prompt;
import static org.testatoo.core.ComponentType.Radio;

public class PromptTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Prompt);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new Prompt(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a Prompt but a Radio"));
        }
    }

    @Test
    public void test_prompt_message() {
        Prompt prompt = new Prompt(evaluator, id);

        when(evaluator.message(prompt)).thenReturn("message");
        assertThat(prompt.message(), is("message"));
    }

    @Test
    public void can_get_buttons() {
        Prompt prompt = new Prompt(evaluator, id);

        Selection<Button> buttons = ListSelection.of(null, null);

        when(evaluator.buttons(prompt)).thenReturn(buttons);
        assertThat(prompt.buttons().size(), is(2));
    }

    @Test
    public void test_toString() {
        Prompt prompt = new Prompt(evaluator, id);

        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.title(prompt)).thenReturn("title");
        when(evaluator.message(prompt)).thenReturn("message");

        assertThat(prompt.toString(), is("class org.testatoo.core.component.Prompt with state : enabled:true, visible:true, title:title, message:message"));
    }
}
