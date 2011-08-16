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
import static org.testatoo.core.ComponentType.ComboBox;
import static org.testatoo.core.ComponentType.Radio;

public class ComboBoxTest {

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
        when(evaluator.componentType(id)).thenReturn(ComboBox);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new ComboBox(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a ComboBox but a Radio"));
        }
    }

    @Test
    public void can_select_a_value() {
        ComboBox comboBox = new ComboBox(evaluator, id);

        when(evaluator.values(any(ComboBox.class))).thenReturn(countries);
        when(evaluator.selectedValues(any(ComboBox.class))).thenReturn(ListSelection.empty());

        comboBox.select("Spain");
        verify(evaluator, times(1)).select("Spain", comboBox);
    }

    @Test
    public void can_obtain_the_value() {
        ComboBox comboBox = new ComboBox(evaluator, id);
        Selection<String> selectedValues = ListSelection.of("myValue");

        when(evaluator.selectedValues(comboBox)).thenReturn(selectedValues);

        assertThat(comboBox.selectedValue(), is("myValue"));
        verify(evaluator, times(1)).selectedValues(comboBox);
    }

    @Test
    public void can_obtain_the_label() {
        when(evaluator.label(any(LabelSupport.class))).thenReturn("myLabel");
        ComboBox comboBox = new ComboBox(evaluator, id);
        assertThat(comboBox.label(), is("myLabel"));
    }

    @Test
    public void can_obtain_list_of_values() {
        Selection<String> selectedCountries = ListSelection.of("Canada");

        when(evaluator.selectedValues(any(ComboBox.class))).thenReturn(selectedCountries);
        when(evaluator.values(any(ComboBox.class))).thenReturn(countries);

        ComboBox comboBox = new ComboBox(evaluator, id);
        assertThat(comboBox.values().toString(), is("[France, Canada, Germany, Italy, Spain]"));
    }

    @Test
    public void test_toString() {
        Selection<String> selectedCountries = ListSelection.of("Canada");

        when(evaluator.selectedValues(any(ComboBox.class))).thenReturn(selectedCountries);
        when(evaluator.label(any(LabelSupport.class))).thenReturn("label");
        when(evaluator.values(any(ComboBox.class))).thenReturn(countries);

        ComboBox comboBox = new ComboBox(evaluator, id);
        assertThat(comboBox.toString(), is("class org.testatoo.core.component.ComboBox with state : enabled:true, visible:true, values:[France, Canada, Germany, Italy, Spain], selectedValues:[Canada], label:label"));
    }

}
