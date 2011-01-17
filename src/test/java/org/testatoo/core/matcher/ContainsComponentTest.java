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

package org.testatoo.core.matcher;

import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.Evaluator;
import org.testatoo.core.ListSelection;
import org.testatoo.core.Selection;
import org.testatoo.core.component.Button;
import org.testatoo.core.component.CheckBox;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.Panel;
import org.testatoo.core.component.Radio;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testatoo.core.ComponentType.Button;
import static org.testatoo.core.ComponentType.CheckBox;
import static org.testatoo.core.ComponentType.Panel;
import static org.testatoo.core.ComponentType.Radio;
import static org.testatoo.core.Language.assertThat;
import static org.testatoo.core.matcher.ContainsComponent.contains;
import static org.testatoo.core.matcher.mock.MockFactory.format;

public class ContainsComponentTest {

    private static String panelId = "panelId";
    private static String checkboxId = "checkboxId";
    private static String radioId = "radioId";
    private static String buttonId = "buttonId";

    private Panel panel;
    private CheckBox checkBox;
    private Radio radio;
    private Button button;


    @Before
    public void beforeMethod() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(panelId)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(panelId)).thenReturn(Panel);
        when(evaluator.title(any(Panel.class))).thenReturn("myPanelTitle");

        panel = new Panel(evaluator, panelId);

        when(evaluator.existComponent(checkboxId)).thenReturn(true);
        when(evaluator.componentType(checkboxId)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(CheckBox.class))).thenReturn("label");
        when(evaluator.isChecked(any(CheckBox.class))).thenReturn(true);

        checkBox = new CheckBox(evaluator, checkboxId);

        when(evaluator.existComponent(radioId)).thenReturn(true);
        when(evaluator.componentType(radioId)).thenReturn(Radio);

        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(Radio.class))).thenReturn("label");
        when(evaluator.isChecked(any(Radio.class))).thenReturn(true);

        radio = new Radio(evaluator, radioId);

        when(evaluator.existComponent(buttonId)).thenReturn(true);
        when(evaluator.componentType(buttonId)).thenReturn(Button);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.icon(any(Button.class))).thenReturn("");
        when(evaluator.text(any(Button.class))).thenReturn("buttonText");

        button = new Button(evaluator, buttonId);

        when(evaluator.contains(panel, checkBox)).thenReturn(true);
        when(evaluator.contains(panel, radio)).thenReturn(true);
        when(evaluator.contains(panel, checkBox, radio)).thenReturn(true);
        when(evaluator.contains(panel, button)).thenReturn(false);
    }

    @Test
    public void test_containsComponent_matcher() {
        assertThat(panel, contains(checkBox));
        assertThat(panel, contains(checkBox, radio));

        Selection<Component> selection = ListSelection.<Component>of(checkBox, radio);
        assertThat(panel, contains(selection));

        assertThat(panel, not(contains(button)));

        try {
            assertThat(button, contains(panel));
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: a container got: <class org.testatoo.core.component.Button with state : enabled:true, visible:true, text:buttonText, icon:>"));
        }

        try {
            assertThat(panel, contains(button));
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: contain one of {<class org.testatoo.core.component.Button with state : enabled:true, visible:true, text:buttonText, icon:>} got: <class org.testatoo.core.component.Panel with state : enabled:true, visible:true, title:myPanelTitle>"));
        }

    }

}
