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

package org.testatoo.core;

import org.testatoo.core.component.*;
import org.testatoo.core.component.datagrid.*;
import org.testatoo.core.input.Click;
import org.testatoo.core.input.Key;
import org.testatoo.core.input.KeyModifier;
import org.testatoo.core.input.KeyboardLayout;
import org.testatoo.core.input.i18n.USEnglishLayout;
import org.testatoo.core.nature.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the abstract base class corresponding to the Evaluator interface.
 * Someone who wants to create an Evaluator for a new technology or a new engine will have to extend this class.
 *
 * @author dev@testatoo.org
 */
public class EvaluatorAdapter<T> implements Evaluator<T> {

    protected final List<KeyModifier> pressedKeyModifier = new ArrayList<KeyModifier>();
    protected KeyboardLayout keyboardLayout = new USEnglishLayout();

    @Override
    public String name() {
        return DEFAULT_NAME;
    }

    @Override
    public void setI18nCharConverter(KeyboardLayout converter) {
        this.keyboardLayout = converter;
    }

    @Override
    public Boolean contains(Container container, Component... component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void click(Component component, Click which) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doubleClick(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseOver(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseOut(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean existComponent(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String icon(IconSupport iconSupport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String text(TextSupport textSupport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void check(Checkable checkable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean isChecked(Checkable checkable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unCheck(CheckBox checkBox) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String value(ValueSupport valueSupport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean isVisible(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean isEnabled(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<String> selectedValues(ListModel listModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unselect(String value, ListModel listModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unselectAll(ListModel listModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<String> values(ListModel listModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void select(String value, ListModel listModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String source(Image image) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String label(LabelSupport labelSupport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer maxLength(AbstractTextField textField) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reset(AbstractTextField textField) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String reference(Link link) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String title(TitleSupport titleSupport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String message(AlertBox alertBox) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<Button> buttons(DialogBox dialogBox) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close(AbstractWindow window) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String message(DialogBox dialogBox) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String message(Prompt prompt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<Column> columns(DataGrid dataGrid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<Row> rows(DataGrid dataGrid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType componentType(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dragAndDrop(Component from, Component to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void keyDown(KeyModifier keyModifier) {
        pressedKeyModifier.add(keyModifier);
    }

    @Override
    public void release(KeyModifier keyModifier) {
        pressedKeyModifier.remove(keyModifier);
    }

    @Override
    public void release() {
        List<KeyModifier> keys = new ArrayList<KeyModifier>(pressedKeyModifier);
        Collections.reverse(keys);
        try {
            for (KeyModifier keyModifier : keys) {
                release(keyModifier);
            }
        } catch (Exception e) {
            // Continue
        }
    }

    @Override
    public Selection<Button> buttons(Prompt prompt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void type(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void press(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean hasFocus(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void selectFilePath(String filePath, FileDialog fileDialog) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String selectedFilePath(FileDialog fileDialog) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void focusOn(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Selection<Cell> cells(CellContainer cellContainer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void open(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String pageSource() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String evaluate(String expression) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T implementation() {
        throw new UnsupportedOperationException();
    }

}
