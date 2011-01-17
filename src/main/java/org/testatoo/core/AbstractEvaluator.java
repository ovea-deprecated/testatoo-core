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

import org.testatoo.core.component.AbstractTextField;
import org.testatoo.core.component.AbstractWindow;
import org.testatoo.core.component.AlertBox;
import org.testatoo.core.component.Button;
import org.testatoo.core.component.CheckBox;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.DialogBox;
import org.testatoo.core.component.FileDialog;
import org.testatoo.core.component.Image;
import org.testatoo.core.component.Link;
import org.testatoo.core.component.ListModel;
import org.testatoo.core.component.Prompt;
import org.testatoo.core.component.datagrid.Cell;
import org.testatoo.core.component.datagrid.CellContainer;
import org.testatoo.core.component.datagrid.Column;
import org.testatoo.core.component.datagrid.DataGrid;
import org.testatoo.core.component.datagrid.Row;
import org.testatoo.core.input.Click;
import org.testatoo.core.input.Key;
import org.testatoo.core.input.KeyModifier;
import org.testatoo.core.input.KeyboardLayout;
import org.testatoo.core.input.i18n.USEnglishLayout;
import org.testatoo.core.nature.Checkable;
import org.testatoo.core.nature.Container;
import org.testatoo.core.nature.IconSupport;
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.TextSupport;
import org.testatoo.core.nature.TitleSupport;
import org.testatoo.core.nature.ValueSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.testatoo.core.ComponentType.Undefined;

/**
 * This is the abstract base class corresponding to the Evaluator interface.
 * Someone who wants to create an Evaluator for a new technology or a new engine will have to extend this class.
 *
 * @author dev@testatoo.org
 */
public abstract class AbstractEvaluator<T> implements Evaluator<T> {

    private Condition condition = ConditionChain.create();
    protected List<KeyModifier> pressedKeyModifier = new ArrayList<KeyModifier>();
    protected KeyboardLayout keyboardLayout = new USEnglishLayout();

    @Override
    public String name() {
        return DEFAULT_NAME;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void setWaitingCondition(Condition condition) {
        if (condition != null) {
            this.condition = condition;
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Condition getWaitingCondition() {
        return condition;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void setI18nCharConverter(KeyboardLayout converter) {
        this.keyboardLayout = converter;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean contains(Container container, Component... component) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void click(Component component, Click which) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void doubleClick(Component component) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void mouseOver(Component component) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void mouseOut(Component component) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean existComponent(String id) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String icon(IconSupport iconSupport) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String text(TextSupport textSupport) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void check(Checkable checkable) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isChecked(Checkable checkable) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void unCheck(CheckBox checkBox) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String value(ValueSupport valueSupport) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isVisible(Component component) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isEnabled(Component component) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<String> selectedValues(ListModel listModel) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void unselect(String value, ListModel listModel) {
    }

    @Override
    public void unselectAll(ListModel listModel) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<String> values(ListModel listModel) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void select(String value, ListModel listModel) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String source(Image image) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String label(LabelSupport labelSupport) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Integer maxLength(AbstractTextField textField) {
        return 0;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void reset(AbstractTextField textField) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String reference(Link link) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String title(TitleSupport titleSupport) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String message(AlertBox alertBox) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Button> buttons(DialogBox dialogBox) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void close(AbstractWindow window) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String message(DialogBox dialogBox) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String message(Prompt prompt) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Column> columns(DataGrid dataGrid) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Row> rows(DataGrid dataGrid) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public ComponentType componentType(String id) {
        return Undefined;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void dragAndDrop(Component from, Component to) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void keyDown(KeyModifier keyModifier) {
        pressedKeyModifier.add(keyModifier);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void release(KeyModifier keyModifier) {
        pressedKeyModifier.remove(keyModifier);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
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

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Button> buttons(Prompt prompt) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void type(String text) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void press(Key key) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean hasFocus(Component component) {
        return FALSE;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void selectFilePath(String filePath, FileDialog fileDialog) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String selectedFilePath(FileDialog fileDialog) {
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void focusOn(Component component) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Cell> cells(CellContainer cellContainer) {
        return ListSelection.empty();
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void open(String url) {
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String pageSource() {
        return null;
    }
}
