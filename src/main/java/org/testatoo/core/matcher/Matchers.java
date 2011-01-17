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

import org.hamcrest.Matcher;
import org.testatoo.core.Selection;
import org.testatoo.core.component.AbstractTextField;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.ListModel;
import org.testatoo.core.component.TextField;
import org.testatoo.core.component.datagrid.Cell;
import org.testatoo.core.component.datagrid.CellContainer;
import org.testatoo.core.component.datagrid.Column;
import org.testatoo.core.component.datagrid.DataGrid;
import org.testatoo.core.component.datagrid.Row;
import org.testatoo.core.nature.Checkable;
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.MultiSelectable;
import org.testatoo.core.nature.SimpleSelectable;
import org.testatoo.core.nature.TextSupport;
import org.testatoo.core.nature.TitleSupport;
import org.testatoo.core.nature.ValueSupport;

/**
 * This abstract class defines all the matchers (based on hamcrest matchers) used in testatoo tests.
 *
 * @author dev@testatoo.org
 */
public abstract class Matchers {

    protected Matchers() {
    }

    /**
     * To test the state enabled of a graphic object, using the syntax "enabled()".
     *
     * @return a new Enabled matcher
     * @see Enabled
     */
    public static Matcher<Component> enabled() {
        return Enabled.enabled();
    }


    /**
     * To test the state disabled of a graphic object, using the syntax "disabled()".
     *
     * @return a new Disabled matcher
     * @see Disabled
     */
    public static Matcher<Component> disabled() {
        return Disabled.disabled();
    }

    /**
     * To test the state visible of a graphic object, using the syntax "visible()".
     *
     * @return a new Visible matcher
     * @see Visible
     */
    public static Matcher<Component> visible() {
        return Visible.visible();
    }

    /**
     * To test the state checked of a checkable object, using the syntax "checked()".
     *
     * @return a new Checked matcher
     * @see Checked
     */
    public static Matcher<Checkable> checked() {
        return Checked.checked();
    }

    /**
     * To test the state unchecked of a checkable object, using the syntax "unChecked()".
     *
     * @return a new UnChecked matcher
     * @see UnChecked
     */
    public static Matcher<Checkable> unChecked() {
        return UnChecked.unChecked();
    }

    /**
     * To test the absence of something : matcher that decorates another matcher
     *
     * @param <T>     the graphic object
     * @param matcher the decorated matcher
     * @return the result of the negative assertion
     */
    public static <T> Matcher<T> no(Matcher<T> matcher) {
        return org.hamcrest.Matchers.not(matcher);
    }

    /**
     * To allow the test to be more expressive, using the syntax "has" before another matcher.
     *
     * @param <T>     the graphic object
     * @param matcher the decorated matcher
     * @return a new Has matcher
     * @see Has
     */
    public static <T> Matcher<T> has(Matcher<T> matcher) {
        return Has.has(matcher);
    }

    /**
     * To test the number of columns of a datagrid, using the syntax "has(3, columns())"
     *
     * @param num     the expected number of columns
     * @param columns the tested dimension
     * @return a new ColumnSize matcher created with the given size
     * @see ColumnSize
     */
    public static Matcher<DataGrid> has(int num, Column[] columns) {
        return ColumnSize.is(num);
    }

    /**
     * To test the number of rows of a datagrid, using the syntax "has(12, rows())"
     *
     * @param num  the expected number of rows
     * @param rows the tested dimension
     * @return a new RowSize matcher created with the given size
     * @see RowSize
     */
    public static Matcher<DataGrid> has(int num, Row[] rows) {
        return RowSize.is(num);
    }

    /**
     * To test the number of cells of a datagrid, using the syntax "has(36, cells())"
     *
     * @param num   the expected number of cells
     * @param cells the tested dimension
     * @return a new CellSize matcher created with the given size
     * @see CellSize
     */
    public static Matcher<CellContainer> has(int num, Cell[] cells) {
        return CellSize.is(num);
    }

    /**
     * To allow the test to be more expressive using the syntax "a" before another matcher
     *
     * @param <T>     the graphic object
     * @param matcher the decorated matcher
     * @return the result of the assertion
     */
    public static <T> Matcher<T> a(Matcher<T> matcher) {
        return matcher;
    }

    /**
     * To test the title of a graphic object that supports title, using the syntax "title("xxxxx")"
     *
     * @param title the expected title
     * @return a new TitleValue matcher
     * @see TitleValue
     */
    public static Matcher<TitleSupport> title(String title) {
        return TitleValue.value(title);
    }

    /**
     * To test the value of a graphic object, using the syntax "value("xxxxx")"
     *
     * @param value the expected value
     * @return a new Value matcher
     * @see Value
     */
    public static Matcher<ValueSupport> value(String value) {
        return Value.value(value);
    }

    /**
     * To test if th value of a graphic object is empty, using the syntax "value()"
     *
     * @return a new Value matcher
     * @see Value
     */
    public static Matcher<ValueSupport> empty() {
        return Value.value("");
    }

    /**
     * To test the text of a graphic object, using the syntax "text("xxxxxxx")"
     *
     * @param text the expected text
     * @return a new TextValue matcher
     * @see TextValue
     */
    public static Matcher<TextSupport> text(String text) {
        return TextValue.text(text);
    }

    /**
     * To test the maximum length of a textField, using the syntax "maxLength(34)"
     *
     * @param maxLength the expected maxLength
     * @return a new MaxLength matcher
     * @see MaxLength
     */
    public static Matcher<AbstractTextField> maxLength(int maxLength) {
        return MaxLength.maxLength(maxLength);
    }

    /**
     * To test the components are contained in a component, using the syntax "contains(component1, component2 ...)"
     *
     * @param components the expected embedded components
     * @return a new ContainsComponent matcher
     * @see ContainsComponent
     */
    public static Matcher<Component> contains(Component... components) {
        return ContainsComponent.contains(components);
    }

    /**
     * To test the components are contained in a component, using the syntax "contains(selection)"
     *
     * @param selection of the expected embedded components
     * @return a new ContainsComponent matcher
     * @see ContainsComponent
     */
    public static Matcher<Component> contains(Selection<Component> selection) {
        return ContainsComponent.contains(selection);
    }

    /**
     * To test if values are contained in a list, using the syntax "containsValues("aaaaa", "bbbb", "cccccc")"
     *
     * @param values the expected values
     * @return a new ContainsValues matcher
     * @see ContainsValues
     */
    public static Matcher<ListModel> containsValues(String... values) {
        return ContainsValues.contains(values);
    }

    /**
     * To test if values are exactly the values contained in a list, using the syntax "containsExactlyValues("aaaaa", "bbbb", "cccccc")"
     *
     * @param values the expected values
     * @return a new ContainsValues matcher
     * @see ContainsValues
     */
    public static Matcher<ListModel> containsExactlyValues(String... values) {
        return ContainsValues.containsExaclty(values);
    }

    /**
     * To test if text are contained in a textfield, using the syntax "containsText("aaaaa")"
     *
     * @param text the expected values
     * @return a new ContainsText matcher
     * @see ContainsText
     */
    public static Matcher<TextField> containsText(String text) {
        return ContainsText.contains(text);
    }

    /**
     * To test if text are exactly the values contained in a textfield, using the syntax "containsExactlyText("aaaaa")"
     *
     * @param text the expected text
     * @return a new ContainsText matcher
     * @see ContainsText
     */
    public static Matcher<TextField> containsExactlyText(String text) {
        return ContainsText.containsExaclty(text);
    }

    /**
     * To test if values are exactly the values selected in a list, using the syntax "selectedValues("aaaaa", "bbbb", "cccccc")"
     *
     * @param values the expected values
     * @return a new SelectedValues matcher
     * @see SelectedValues
     */
    public static Matcher<MultiSelectable> selectedValues(String... values) {
        return SelectedValues.selectedValues(values);
    }

    /**
     * To test if a value is the value selected in a single selection list, using the syntax "selectedValue("aaaaa")"
     *
     * @param value the expected value
     * @return a new SelectedValue matcher
     * @see SelectedValue
     */
    public static Matcher<SimpleSelectable> selectedValue(String value) {
        return SelectedValue.selectedValue(value);
    }

    /**
     * To test if a graphic object has an empty label, using the syntax "emptyLabel()"
     *
     * @return a new LabelValue matcher
     * @see LabelValue
     */
    public static Matcher<LabelSupport> emptyLabel() {
        return LabelValue.emptyLabel();
    }

    /**
     * To test the label of a graphic object, using the syntax "label("xxxx")"
     *
     * @param label the expected label
     * @return a new LabelValue matcher
     * @see LabelValue
     */
    public static Matcher<LabelSupport> label(String label) {
        return LabelValue.label(label);
    }

    /**
     * To test if a graphic object has a not empty label, using the syntax "label()"
     *
     * @return a new LabelValue matcher
     * @see LabelValue
     */
    public static Matcher<LabelSupport> label() {
        return LabelValue.label();
    }


}
