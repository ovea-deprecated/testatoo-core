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

package org.testatoo.core.matcher.mock;

import org.testatoo.core.*;
import org.testatoo.core.component.*;
import org.testatoo.core.component.datagrid.*;
import org.testatoo.core.nature.*;

import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.*;

public class MockFactory {

    private static String id = "myId";

    public static Component enabledComponent() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);

        return new Component(evaluator, id);
    }

    public static Component disabledComponent() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(false);

        return new Component(evaluator, id);
    }

    public static Component visibleComponent() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);

        return new Component(evaluator, id);
    }

    public static Component invisibleComponent() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(false);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);

        return new Component(evaluator, id);
    }

    public static Component existentComponent(Evaluator evaluator) {
        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(false);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(false);

        return new Component(evaluator, id);
    }

    public static Component inExistentComponent(Evaluator evaluator) {
        when(evaluator.existComponent(id)).thenReturn(true, false);
        when(evaluator.isVisible(any(Component.class))).thenReturn(false);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(false);

        return new Component(evaluator, id);
    }

    public static CheckBox checkedCheckBox() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(CheckBox.class))).thenReturn("label");
        when(evaluator.isChecked(any(CheckBox.class))).thenReturn(true);

        return new CheckBox(evaluator, id);
    }

    public static CheckBox unCheckedCheckBox() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(CheckBox.class))).thenReturn("label");
        when(evaluator.isChecked(any(CheckBox.class))).thenReturn(false);

        return new CheckBox(evaluator, id);
    }

    public static Radio checkedRadio() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(Radio.class))).thenReturn("label");
        when(evaluator.isChecked(any(Radio.class))).thenReturn(true);

        return new Radio(evaluator, id);
    }

    public static Radio unCheckedRadio() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(Radio.class))).thenReturn("label");
        when(evaluator.isChecked(any(Radio.class))).thenReturn(false);

        return new Radio(evaluator, id);
    }

    public static Field field(final String value) {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(Field.class))).thenReturn("label");
        when(evaluator.value(any(Field.class))).thenReturn(value);

        return new FieldImpl(evaluator, id);
    }

    public static ComboBox comboBox() {
        Evaluator evaluator = mock(Evaluator.class);

        Selection<String> selectedValues = ListSelection.of("Canada");

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(ComboBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(LabelSupport.class))).thenReturn("label");
        when(evaluator.values(any(ComboBox.class))).thenReturn(countries());
        when(evaluator.selectedValues(any(ComboBox.class))).thenReturn(selectedValues);

        return new ComboBox(evaluator, id);
    }

    public static ListBox listBox() {
        Evaluator evaluator = mock(Evaluator.class);
        Selection<String> selectedValues = ListSelection.of("Canada", "Spain");

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(ListBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(LabelSupport.class))).thenReturn("label");
        when(evaluator.values(any(ListBox.class))).thenReturn(countries());
        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(selectedValues);

        return new ListBox(evaluator, id);
    }

    public static ListBox listBoxWithoutSelectedValue() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(ListBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.values(any(ListBox.class))).thenReturn(countries());
        when(evaluator.selectedValues(any(ListBox.class))).thenReturn(ListSelection.empty());

        return new ListBox(evaluator, id);
    }

    public static CheckBox checkBoxWithoutLabel() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.isChecked(any(CheckBox.class))).thenReturn(true);
        when(evaluator.label(any(CheckBox.class))).thenReturn("");

        return new CheckBox(evaluator, id);
    }

    public static CheckBox checkBoxWithLabel(final String label) {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(CheckBox);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.isChecked(any(CheckBox.class))).thenReturn(true);
        when(evaluator.label(any(CheckBox.class))).thenReturn(label);

        return new CheckBox(evaluator, id);
    }

    public static Button button() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Button);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.icon(any(Button.class))).thenReturn("");
        when(evaluator.text(any(Button.class))).thenReturn("buttonText");

        return new Button(evaluator, id);
    }

    public static SizeSupport sizeSupport() {
        VirtualComponent component = mock(VirtualComponent.class);
        when(component.size()).thenReturn(2);
        when(component.toString()).thenReturn("size:2");
        return component;
    }

    public static TextField textFieldWithMaxLength(final int maxLength) {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(TextField);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(TextField.class))).thenReturn("label");
        when(evaluator.value(any(TextField.class))).thenReturn("textFieldValue");
        when(evaluator.maxLength(any(TextField.class))).thenReturn(maxLength);

        return new TextField(evaluator, id);
    }

    public static TextField simpleTextFieldWithText(final String text) {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(TextField);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.label(any(TextField.class))).thenReturn("label");
        when(evaluator.value(any(TextField.class))).thenReturn(text);
        when(evaluator.maxLength(any(TextField.class))).thenReturn(50);

        return new TextField(evaluator, id);
    }

    public static DataGrid dataGrid() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(any(String.class))).thenReturn(true);
        when(evaluator.componentType("cell_1")).thenReturn(Cell);
        when(evaluator.componentType("cell_2")).thenReturn(Cell);
        when(evaluator.componentType("cell_3")).thenReturn(Cell);
        when(evaluator.componentType("cell_4")).thenReturn(Cell);
        when(evaluator.componentType("cell_5")).thenReturn(Cell);
        when(evaluator.componentType("cell_6")).thenReturn(Cell);

        Cell cell_1 = new Cell(evaluator, "cell_1");
        Cell cell_2 = new Cell(evaluator, "cell_2");
        Selection<Cell> cellList_1 = ListSelection.of(cell_1, cell_2);

        when(evaluator.value(cell_1)).thenReturn("A");
        when(evaluator.value(cell_2)).thenReturn("B");

        Cell cell_3 = new Cell(evaluator, "cell_3");
        Cell cell_4 = new Cell(evaluator, "cell_4");
        Selection<Cell> cellList_2 = ListSelection.of(cell_3, cell_4);

        when(evaluator.value(cell_3)).thenReturn("C");
        when(evaluator.value(cell_4)).thenReturn("D");

        Cell cell_5 = new Cell(evaluator, "cell_5");
        Cell cell_6 = new Cell(evaluator, "cell_6");
        Selection<Cell> cellList_3 = ListSelection.of(cell_5, cell_6);

        when(evaluator.value(cell_5)).thenReturn("E");
        when(evaluator.value(cell_6)).thenReturn("F");

        when(evaluator.componentType("col_1")).thenReturn(Column);
        when(evaluator.componentType("col_2")).thenReturn(Column);
        when(evaluator.componentType("col_3")).thenReturn(Column);

        Column column_1 = new Column(evaluator, "col_1");
        Column column_2 = new Column(evaluator, "col_2");
        Column column_3 = new Column(evaluator, "col_3");
        Selection<Column> columns = ListSelection.of(column_1, column_2, column_3);

        when(evaluator.title(column_1)).thenReturn("Column 1");
        when(evaluator.title(column_2)).thenReturn("Column 2");
        when(evaluator.title(column_3)).thenReturn("Column 3");

        when(evaluator.cells(column_1)).thenReturn(cellList_1);
        when(evaluator.cells(column_2)).thenReturn(cellList_2);
        when(evaluator.cells(column_3)).thenReturn(cellList_3);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(DataGrid);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);

        DataGrid dataGrid = new DataGrid(evaluator, id);
        when(evaluator.columns(dataGrid)).thenReturn(columns);
        when(evaluator.componentType("row_1")).thenReturn(Row);

        Row row = new Row(evaluator, "row_1");
        Selection<Row> rows = ListSelection.of(row);

        when(evaluator.rows(dataGrid)).thenReturn(rows);
        when(evaluator.cells(row)).thenReturn(cellList_1);

        return dataGrid;
    }

    public static Panel panel() {
        Evaluator evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.isVisible(any(Component.class))).thenReturn(true);
        when(evaluator.isEnabled(any(Component.class))).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Panel);
        when(evaluator.title(any(Panel.class))).thenReturn("myPanelTitle");

        return new Panel(evaluator, id);
    }

    public static String format(String message) {
        String formatedMessage = message.replace("\n", "");
        formatedMessage = formatedMessage.replace("    ", "");
        return formatedMessage;
    }

    private static Selection<String> countries() {
        return ListSelection.of("France", "Canada", "Germany", "Italy", "Spain");
    }

    private static abstract class VirtualComponent implements SizeSupport {
    }

}
