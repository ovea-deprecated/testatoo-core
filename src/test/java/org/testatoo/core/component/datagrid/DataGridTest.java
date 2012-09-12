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

import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.ComponentException;
import org.testatoo.core.Evaluator;
import org.testatoo.core.ListSelection;
import org.testatoo.core.Selection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.testatoo.core.ComponentType.Cell;
import static org.testatoo.core.ComponentType.Column;
import static org.testatoo.core.ComponentType.DataGrid;
import static org.testatoo.core.ComponentType.*;
import static org.testatoo.core.ComponentType.Row;

/**
 * @author dev@testatoo.org
 */
public class DataGridTest {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(DataGrid);
    }

    @Test
    public void test_component_type() {
        evaluator = mock(Evaluator.class);

        when(evaluator.existComponent(id)).thenReturn(true);
        when(evaluator.componentType(id)).thenReturn(Radio);

        try {
            new DataGrid(evaluator, id);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("The component with id=" + id + " is not a DataGrid but a Radio"));
        }
    }

    @Test
    public void can_obtain_columns() {
        when(evaluator.existComponent("1")).thenReturn(true);
        when(evaluator.componentType("1")).thenReturn(Column);

        when(evaluator.existComponent("2")).thenReturn(true);
        when(evaluator.componentType("2")).thenReturn(Column);

        when(evaluator.existComponent("3")).thenReturn(true);
        when(evaluator.componentType("3")).thenReturn(Column);

        when(evaluator.existComponent("4")).thenReturn(true);
        when(evaluator.componentType("4")).thenReturn(Column);

        DataGrid dataGrid = new DataGrid(evaluator, id);

        Column column_1 = new Column(evaluator, "1");
        Column column_2 = new Column(evaluator, "2");
        Column column_3 = new Column(evaluator, "3");
        Column column_4 = new Column(evaluator, "4");

        Selection<Column> columns = ListSelection.of(column_1, column_2, column_3, column_4);

        when(evaluator.columns(dataGrid)).thenReturn(columns);
        when(evaluator.title(column_1)).thenReturn("Column1");
        when(evaluator.title(column_2)).thenReturn("Column2");
        when(evaluator.title(column_3)).thenReturn("Column3");
        when(evaluator.title(column_4)).thenReturn("Column4");

        assertThat(dataGrid.columns().size(), is(4));
        assertThat(dataGrid.column(1).title(), is("Column1"));
        assertThat(dataGrid.column(2).title(), is("Column2"));
        assertThat(dataGrid.column(3).title(), is("Column3"));
        assertThat(dataGrid.column(4).title(), is("Column4"));
    }

    @Test
    public void can_test_columns_title() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("1")).thenReturn(true);
        when(evaluator.componentType("1")).thenReturn(Column);

        Column column_1 = new Column(evaluator, "1");
        Selection<Column> columns = ListSelection.of(column_1);

        when(evaluator.columns(dataGrid)).thenReturn(columns);
        when(evaluator.title(column_1)).thenReturn("ColumnTitle");

        assertThat(dataGrid.columns().get(0).title(), is("ColumnTitle"));
        assertThat(dataGrid.column(1).title(), is("ColumnTitle"));
    }

    @Test
    public void can_obtain_cell_in_column() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("column")).thenReturn(true);
        when(evaluator.componentType("column")).thenReturn(Column);

        when(evaluator.existComponent("1")).thenReturn(true);
        when(evaluator.componentType("1")).thenReturn(Cell);

        when(evaluator.existComponent("2")).thenReturn(true);
        when(evaluator.componentType("2")).thenReturn(Cell);

        when(evaluator.existComponent("3")).thenReturn(true);
        when(evaluator.componentType("3")).thenReturn(Cell);

        Column column = new Column(evaluator, "column");
        Selection<Column> columns = ListSelection.of(column);

        Cell cell_1 = new Cell(evaluator, "1");
        Cell cell_2 = new Cell(evaluator, "2");
        Cell cell_3 = new Cell(evaluator, "3");
        Selection<Cell> cells = ListSelection.of(cell_1, cell_2, cell_3);

        when(evaluator.value(cell_1)).thenReturn("value1");
        when(evaluator.value(cell_2)).thenReturn("value2");
        when(evaluator.value(cell_3)).thenReturn("value3");

        when(evaluator.columns(dataGrid)).thenReturn(columns);
        when(evaluator.cells(column)).thenReturn(cells);

        assertThat(dataGrid.columns().get(0).cells().size(), is(3));

        assertThat(dataGrid.columns().get(0).cells().get(0).value(), is("value1"));
        assertThat(dataGrid.columns().get(0).cells().get(1).value(), is("value2"));
        assertThat(dataGrid.columns().get(0).cells().get(2).value(), is("value3"));

        assertThat(dataGrid.column(1).cell(1).value(), is("value1"));
        assertThat(dataGrid.column(1).cell(2).value(), is("value2"));
        assertThat(dataGrid.column(1).cell(3).value(), is("value3"));
    }

    @Test
    public void test_column_position_exception() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("column")).thenReturn(true);
        when(evaluator.componentType("column")).thenReturn(Column);

        Column column = new Column(evaluator, "column");
        Selection<Column> columns = ListSelection.of(column);

        when(evaluator.title(column)).thenReturn("ColumnTitle");
        when(evaluator.columns(dataGrid)).thenReturn(columns);

        try {
            dataGrid.column(5);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Column 5 is not available"));
        }
    }

    @Test
    public void test_cell_position_exception() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("column")).thenReturn(true);
        when(evaluator.componentType("column")).thenReturn(Column);

        Column column = new Column(evaluator, "column");
        when(evaluator.title(column)).thenReturn("ColumnTitle");

        Selection<Column> columns = ListSelection.of(column);

        when(evaluator.columns(dataGrid)).thenReturn(columns);

        when(evaluator.existComponent("cell_1")).thenReturn(true);
        when(evaluator.componentType("cell_1")).thenReturn(Cell);

        when(evaluator.existComponent("cell_2")).thenReturn(true);
        when(evaluator.componentType("cell_2")).thenReturn(Cell);

        when(evaluator.existComponent("cell_3")).thenReturn(true);
        when(evaluator.componentType("cell_3")).thenReturn(Cell);

        Cell cell_1 = new Cell(evaluator, "cell_1");
        Cell cell_2 = new Cell(evaluator, "cell_2");
        Cell cell_3 = new Cell(evaluator, "cell_3");
        Selection<Cell> cells = ListSelection.of(cell_1, cell_2, cell_3);

        when(evaluator.cells(column)).thenReturn(cells);
        when(evaluator.value(cell_1)).thenReturn("value1");
        when(evaluator.value(cell_1)).thenReturn("value2");
        when(evaluator.value(cell_1)).thenReturn("value3");

        try {
            dataGrid.column(1).cell(4);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Cell 4 is not available"));
        }
    }

    @Test
    public void test_row_position_exception() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("row_1")).thenReturn(true);
        when(evaluator.componentType("row_1")).thenReturn(Row);

        when(evaluator.existComponent("row_2")).thenReturn(true);
        when(evaluator.componentType("row_2")).thenReturn(Row);

        Row row_1 = new Row(evaluator, "row_1");
        Row row_2 = new Row(evaluator, "row_2");
        Selection<Row> rows = ListSelection.of(row_1, row_2);

        when(evaluator.rows(dataGrid)).thenReturn(rows);

        try {
            dataGrid.row(3);
            fail();
        } catch (ComponentException e) {
            assertThat(e.getMessage(), is("Row 3 is not available"));
        }
    }

    @Test
    public void can_obtain_rows() {
        final DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("1")).thenReturn(true);
        when(evaluator.componentType("1")).thenReturn(Row);

        when(evaluator.existComponent("2")).thenReturn(true);
        when(evaluator.componentType("2")).thenReturn(Row);

        when(evaluator.existComponent("3")).thenReturn(true);
        when(evaluator.componentType("3")).thenReturn(Row);

        when(evaluator.existComponent("4")).thenReturn(true);
        when(evaluator.componentType("4")).thenReturn(Row);

        when(evaluator.existComponent("5")).thenReturn(true);
        when(evaluator.componentType("5")).thenReturn(Row);

        when(evaluator.existComponent("6")).thenReturn(true);
        when(evaluator.componentType("6")).thenReturn(Row);

        when(evaluator.existComponent("7")).thenReturn(true);
        when(evaluator.componentType("7")).thenReturn(Row);

        Row row_1 = new Row(evaluator, "1");
        Row row_2 = new Row(evaluator, "2");
        Row row_3 = new Row(evaluator, "3");
        Row row_4 = new Row(evaluator, "4");
        Row row_5 = new Row(evaluator, "5");
        Row row_6 = new Row(evaluator, "6");
        Row row_7 = new Row(evaluator, "7");
        Selection<Row> rows = ListSelection.of(row_1, row_2, row_3, row_4, row_5, row_6, row_7);

        when(evaluator.rows(dataGrid)).thenReturn(rows);

        assertThat(dataGrid.rows().size(), is(7));
    }

    @Test
    public void can_obtain_cell_in_row() {
        DataGrid dataGrid = new DataGrid(evaluator, id);

        when(evaluator.existComponent("row1")).thenReturn(true);
        when(evaluator.componentType("row1")).thenReturn(Row);

        when(evaluator.existComponent("row2")).thenReturn(true);
        when(evaluator.componentType("row2")).thenReturn(Row);

        when(evaluator.existComponent("1_1")).thenReturn(true);
        when(evaluator.componentType("1_1")).thenReturn(Cell);
        when(evaluator.existComponent("1_2")).thenReturn(true);
        when(evaluator.componentType("1_2")).thenReturn(Cell);
        when(evaluator.existComponent("1_3")).thenReturn(true);
        when(evaluator.componentType("1_3")).thenReturn(Cell);

        when(evaluator.existComponent("2_1")).thenReturn(true);
        when(evaluator.componentType("2_1")).thenReturn(Cell);
        when(evaluator.existComponent("2_2")).thenReturn(true);
        when(evaluator.componentType("2_2")).thenReturn(Cell);
        when(evaluator.existComponent("2_3")).thenReturn(true);
        when(evaluator.componentType("2_3")).thenReturn(Cell);

        Row row1 = new Row(evaluator, "row1");

        Cell cell_1_1 = new Cell(evaluator, "1_1");
        when(evaluator.value(cell_1_1)).thenReturn("value1");
        Cell cell_1_2 = new Cell(evaluator, "1_2");
        when(evaluator.value(cell_1_2)).thenReturn("value2");
        Cell cell_1_3 = new Cell(evaluator, "1_3");
        when(evaluator.value(cell_1_3)).thenReturn("value3");

        Selection<Cell> cells1 = ListSelection.of(cell_1_1, cell_1_2, cell_1_3);

        Row row2 = new Row(evaluator, "row2");

        Cell cell_2_1 = new Cell(evaluator, "2_1");
        when(evaluator.value(cell_2_1)).thenReturn("value4");
        Cell cell_2_2 = new Cell(evaluator, "2_2");
        when(evaluator.value(cell_2_2)).thenReturn("value5");
        Cell cell_2_3 = new Cell(evaluator, "2_3");
        when(evaluator.value(cell_2_3)).thenReturn("value6");

        Selection<Cell> cells2 = ListSelection.of(cell_2_1, cell_2_2, cell_2_3);

        Selection<Row> rows = ListSelection.of(row1, row2);
        when(evaluator.rows(dataGrid)).thenReturn(rows);
        when(evaluator.cells(row1)).thenReturn(cells1);
        when(evaluator.cells(row2)).thenReturn(cells2);

        assertThat(dataGrid.rows().size(), is(2));

        assertThat(dataGrid.rows().get(0).cells().get(0).value(), is("value1"));
        assertThat(dataGrid.rows().get(0).cells().get(1).value(), is("value2"));
        assertThat(dataGrid.rows().get(0).cells().get(2).value(), is("value3"));

        assertThat(dataGrid.rows().get(1).cells().get(0).value(), is("value4"));
        assertThat(dataGrid.rows().get(1).cells().get(1).value(), is("value5"));
        assertThat(dataGrid.rows().get(1).cells().get(2).value(), is("value6"));

        assertThat(dataGrid.row(1).cell(1).value(), is("value1"));
        assertThat(dataGrid.row(1).cell(2).value(), is("value2"));
        assertThat(dataGrid.row(1).cell(3).value(), is("value3"));

        assertThat(dataGrid.row(2).cell(1).value(), is("value4"));
        assertThat(dataGrid.row(2).cell(2).value(), is("value5"));
        assertThat(dataGrid.row(2).cell(3).value(), is("value6"));
    }
}
