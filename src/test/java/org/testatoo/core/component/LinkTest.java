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
import static org.testatoo.core.ComponentType.Link;
import static org.testatoo.core.ComponentType.Radio;

public class LinkTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Link);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new Link(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a Link but a Radio"));
        }
    }

    @Test
    public void test_link_text() {
        Link link = new Link(evaluator, id);

        when(evaluator.text(link)).thenReturn("linkText");
        assertThat(link.text(), is("linkText"));
    }

    @Test
    public void test_link_reference() {
        Link link = new Link(evaluator, id);

        when(evaluator.reference(link)).thenReturn("http://linkReference");
        assertThat(link.reference(), is("http://linkReference"));
    }

    @Test
    public void test_toString() {
        Link link = new Link(evaluator, id);

        when(evaluator.text(link)).thenReturn("linkText");
        when(evaluator.reference(link)).thenReturn("http://linkReference");

        assertThat(link.toString(), is("class org.testatoo.core.component.Link with state : enabled:true, visible:true, text:linkText, reference:http://linkReference"));
    }
}
