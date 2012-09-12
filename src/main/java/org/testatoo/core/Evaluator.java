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
import org.testatoo.core.nature.Checkable;
import org.testatoo.core.nature.Container;
import org.testatoo.core.nature.IconSupport;
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.TextSupport;
import org.testatoo.core.nature.TitleSupport;
import org.testatoo.core.nature.ValueSupport;

/**
 * This interface gives the methods that an Evaluator must have, whatever technology or UI Test engine used
 *
 * @author dev@testatoo.org
 */
public interface Evaluator<T> {

    String DEFAULT_NAME = Evaluator.class.getName() + ".DEFAULT";

    /**
     * @return The implementation used by this Evaluator. This can be useful to recover the underlying implementation for specific cases
     */
    T implementation();

    String name();

    /**
     * Set the language specific char converter
     *
     * @param converter, the language specific converter (by default USEnglishLayout)
     */
    public void setI18nCharConverter(KeyboardLayout converter);

    /**
     * To know if a given container contains a given component
     *
     * @param container an element with container properties (ex : window, panel,..)
     * @param component the component we want to know if contained in the container
     * @return True if the component is contained in the container
     */
    Boolean contains(Container container, Component... component);

    /**
     * To click on a given component
     *
     * @param component the component we want to click on
     * @param which     button is click
     */
    void click(Component component, Click which);

    /**
     * To double-click on a given component
     *
     * @param component the component we want to double-click on
     */
    void doubleClick(Component component);

    /**
     * To move the mouse cursor over a component
     *
     * @param component the component we want to move the mouse cursor over
     */
    void mouseOver(Component component);

    /**
     * To move the mouse cursor out of a component
     *
     * @param component the component we want to move the mouse cursor out
     */
    void mouseOut(Component component);

    /**
     * To know if the given id corresponds to an existing component
     *
     * @param id the id of the component
     * @return True if a component with this id exists
     */
    Boolean existComponent(String id);

    /**
     * To get information about the icon displayed on a given Component with IconSupport
     *
     * @param iconSupport the component with the icon
     * @return the information about the icon
     */
    String icon(IconSupport iconSupport);

    /**
     * To get the text displayed on a TextSupport component
     *
     * @param textSupport component
     * @return the displayed text
     */
    String text(TextSupport textSupport);

    /**
     * To check an element
     *
     * @param checkable an element that can be check (ex : radioButton, checkbox) to check
     */
    void check(Checkable checkable);

    /**
     * To know if a checkable element is checked
     *
     * @param checkable an element that can be check (ex : radioButton, checkBox)
     * @return True if the element is checked
     */
    Boolean isChecked(Checkable checkable);

    /**
     * To uncheck a checkBox
     *
     * @param checkBox the checkBox to uncheck
     */
    void unCheck(CheckBox checkBox);

    /**
     * To get the value displayed in a field
     *
     * @param valueSupport the field we want the value
     * @return the string displayed in the field
     */
    String value(ValueSupport valueSupport);

    /**
     * To know if a given component is visible
     *
     * @param component the component we want to know if visible
     * @return True if the component is visible
     */
    Boolean isVisible(Component component);

    /**
     * To know if a given component is enabled
     *
     * @param component the component we want to know if enabled
     * @return True if the component is enabled
     */
    Boolean isEnabled(Component component);

    /**
     * To get the choosen values in a list
     *
     * @param listModel a Component that represents a list
     * @return the selection of the strings selected in the list
     */
    Selection<String> selectedValues(ListModel listModel);

    /**
     * To unselect a given value in a list
     *
     * @param value     the value we want to be unselected
     * @param listModel the Component that represents a list
     */
    void unselect(String value, ListModel listModel);

    /**
     * To unselect all the selected values in list
     *
     * @param listModel the Component that represents a list
     */
    void unselectAll(ListModel listModel);

    /**
     * To get the values of a list
     *
     * @param listModel a Component that represents a list
     * @return the selection of the strings in the list
     */
    Selection<String> values(ListModel listModel);

    /**
     * To select a given value in a list
     *
     * @param value     the value we want to be selected
     * @param listModel the Component that represents a list
     */
    void select(String value, ListModel listModel);

    /**
     * To get the source of an image
     *
     * @param image the image
     * @return the source
     */
    String source(Image image);

    /**
     * To get the label of an element with label
     *
     * @param labelSupport an element that can have a associated label
     * @return the label associated to the element
     */
    String label(LabelSupport labelSupport);

    /**
     * To get the maximum length of a value of a given textField
     *
     * @param textField the textfield
     * @return the maximum length the value of the textField can have
     */
    Integer maxLength(AbstractTextField textField);

    /**
     * To reset a Textfield
     *
     * @param textField the textfield to reset
     */
    void reset(AbstractTextField textField);

    /**
     * To get the reference of a given link
     *
     * @param link the link
     * @return the reference of the link
     */
    String reference(Link link);

    /**
     * To get the title of a given element
     *
     * @param titleSupport an element that can have a title
     * @return the title of the element
     */
    String title(TitleSupport titleSupport);

    /**
     * To get the message displayed in a given alertBox
     *
     * @param alertBox the alertBox where the message is displayed
     * @return the message of the alertBox
     */
    String message(AlertBox alertBox);

    /**
     * To get the list of buttons of a given dialogBox
     *
     * @param dialogBox the dialogBox where the buttons are
     * @return the selection of Button objects displayed in the dialogBox
     */
    Selection<Button> buttons(DialogBox dialogBox);

    /**
     * To close a given window
     *
     * @param window the window we want to close
     */
    void close(AbstractWindow window);

    /**
     * To get the message of a given dialogBox
     *
     * @param dialogBox the dialog box
     * @return the message displayed in the dialogBox
     */
    String message(DialogBox dialogBox);

    /**
     * To get the message of a given prompt window
     *
     * @param prompt the prompt window
     * @return the message displayed in the prompt window
     */
    String message(Prompt prompt);

    /**
     * To get the columns of a given dataGrid
     *
     * @param dataGrid the datagrid
     * @return the colums of the dataGrid
     */
    Selection<Column> columns(DataGrid dataGrid);

    /**
     * To get the rows of a given dataGrid
     *
     * @param dataGrid the datagrid
     * @return the rows of the dataGrid
     */
    Selection<Row> rows(DataGrid dataGrid);

    /**
     * To know the type of the component corresponding to a given id
     *
     * @param id the id of the component
     * @return the type of the component
     */
    ComponentType componentType(String id);

    /**
     * To do a "drag and drop" operation
     *
     * @param from the component we drag
     * @param to   the component we drop on
     */
    void dragAndDrop(Component from, Component to);

    /**
     * To get the list of buttons of a given prompt window
     *
     * @param prompt the prompt window where the buttons are
     * @return the selection of Button objects displayed in the prompt window
     */
    Selection<Button> buttons(Prompt prompt);

    /**
     * To type a text
     *
     * @param text the text to type
     */
    void type(String text);

    /**
     * To press a Key
     *
     * @param key the Key to press
     */
    void press(Key key);

    /**
     * To KeyDown a KeyModifier
     *
     * @param keyModifier the keyModifier to keyDown
     */
    void keyDown(KeyModifier keyModifier);

    /**
     * To release a KeyModifier
     *
     * @param keyModifier the keyModifier to release
     */
    void release(KeyModifier keyModifier);

    /**
     * To release all KeyModifier
     */
    void release();

    /**
     * To know if a given component has the focus
     *
     * @param component the component
     * @return True if the component has the focus
     */
    Boolean hasFocus(Component component);

    /**
     * To set the complete name (including path) of the file in a given fileDialog
     *
     * @param filePath   the complete name of the file to set in the fileDialog
     * @param fileDialog the fileDialog
     */
    void selectFilePath(String filePath, FileDialog fileDialog);

    /**
     * To get the complete path towards the selected File in a given fileDialog
     *
     * @param fileDialog the fileDialog
     * @return the complete path towards the selected File
     */
    String selectedFilePath(FileDialog fileDialog);

    /**
     * To get the focus on the specified component
     *
     * @param component the component to focus
     */
    void focusOn(Component component);

    /**
     * To get the cells on the specified column
     *
     * @param cellContainer the cellContainer
     * @return selection of cells
     */
    Selection<Cell> cells(CellContainer cellContainer);

    /**
     * To open the page.
     *
     * @param url the url corresponding to the page we want to open
     */
    void open(String url);

    /**
     * To get the source corresponding to the page.
     *
     * @return the page source
     */
    String pageSource();

    String evaluate(String expression);
}
