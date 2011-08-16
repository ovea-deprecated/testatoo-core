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
import org.testatoo.core.nature.LabelSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.ListBox;
import static org.testatoo.core.ComponentType.Radio;

public class ListBoxTest {

    private Evaluator evaluator;
    private String id = "myId";
    private Selection<String> countries;

    @Before
    public void setUp() {
        countries = ListSelection.of("France", "Canada", "Germany", "Italy", "Spain");
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(ListBox);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new ListBox(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a ListBox but a Radio"));
        }
    }

    @Test
    public void can_select_a_value() {
        Selection<String> selectedCountries = ListSelection.of("Canada");

        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries);

        ListBox listBox = new ListBox(evaluator, id);

        listBox.select("Spain");
        verify(evaluator, times(1)).select("Spain", listBox);
    }

    @Test
    public void can_unselect_a_value() {
        Selection<String> selectedCountries = ListSelection.of("Canada");

        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries);

        ListBox listBox = new ListBox(evaluator, id);

        listBox.unSelect("Canada");
        verify(evaluator, times(1)).unselect("Canada", listBox);
    }

    @Test
    public void can_unselect_all_selected_values() {
        ListBox listBox = new ListBox(evaluator, id);

        evaluator.unselectAll(listBox);
        verify(evaluator, times(1)).unselectAll(listBox);
    }

    @Test
    public void can_obtain_the_selected_values() {
        Selection<String> selectedCountries = ListSelection.of("Canada", "Spain");

        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries);

        ListBox listBox = new ListBox(evaluator, id);

        assertThat(listBox.selectedValues().toString(), is("[Canada, Spain]"));
        verify(evaluator, times(1)).selectedValues(any(ListBox.class));
    }

    @Test
    public void can_obtain_list_of_values() {
        Selection<String> selectedCountries = ListSelection.of("Canada", "Spain");

        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries);

        ListBox listBox = new ListBox(evaluator, id);
        assertThat(listBox.values().toString(), is("[France, Canada, Germany, Italy, Spain]"));
        assertThat(listBox.selectedValues().toString(), is("[Canada, Spain]"));

        verify(evaluator, times(1)).selectedValues(listBox);
        verify(evaluator, times(1)).values(listBox);
    }

    @Test
    public void can_obtain_the_label() {
        when(evaluator.label(any(LabelSupport.class))).thenReturn("myLabel");

        ListBox listBox = new ListBox(evaluator, id);
        assertThat(listBox.label(), is("myLabel"));
    }

    @Test
    public void test_toString() {
        Selection<String> selectedCountries = ListSelection.of("Canada", "Spain");

        when(evaluator.label(any(LabelSupport.class))).thenReturn("label");
        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries);

        ListBox listBox = new ListBox(evaluator, id);
        assertThat(listBox.toString(), is("class org.testatoo.core.component.ListBox with state : enabled:true, visible:true, values:[France, Canada, Germany, Italy, Spain], selectedValues:[Canada, Spain], label:label"));
    }
}
