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

package org.testatoo.core.component.datagrid;

import org.testatoo.core.ComponentException;
import org.testatoo.core.Evaluator;
import org.testatoo.core.Selection;
import org.testatoo.core.component.Component;

import java.util.NoSuchElementException;

import static org.testatoo.core.ComponentType.DataGrid;

/**
 * This class allows the testing of a DataGrid properties.
 * The DataGrid is a graphical element that presents a tabular view of data to the user, with rows, columns and cells.
 *
 * @author dev@testatoo.org
 */
public class DataGrid extends Component {

    /**
     * Class constructor specifying the evaluator to use and the id of the dataGrid we want to test.
     * The constructor checks if the given id does correspond to a dataGrid.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the dataGrid
     */
    public DataGrid(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(DataGrid);
    }

    /**
     * To get the colums of the dataGrid
     *
     * @return the list of Column objects in the dataGrid
     */
    public Selection<Column> columns() {
        return evaluator.columns(this);
    }

    /**
     * To get the column at the given position in the datagrid
     *
     * @param position the position of the column we want (1rst column is at position 1)
     * @return the column at the given position in the datagrid
     */
    public Column column(int position) {
        try {
            return columns().get(position - 1);
        } catch (NoSuchElementException e) {
            throw new ComponentException("Column " + position + " is not available", e);
        }
    }

    /**
     * To get the list of rows of the dataGrid
     *
     * @return the list of Row objects in the dataGrid
     */
    public Selection<Row> rows() {
        return evaluator.rows(this);
    }

    /**
     * To get the row at the given position in the datagrid
     *
     * @param position the position of the row we want (1rst row is at position 1)
     * @return the row at the given position in the datagrid
     */
    public Row row(int position) {
        try {
            return rows().get(position - 1);
        } catch (NoSuchElementException e) {
            throw new ComponentException("Row " + position + " is not available", e);
        }
    }

    /**
     * @see org.testatoo.core.component.Component
     */
    @Override
    public String toString() {
        return super.toString() + ", column(s):" + columns().size() + ", row(s):" + rows().size();
    }
}
