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

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.testatoo.core.component.datagrid.DataGrid;

/**
 * This class is a matcher (written based on hamcrest possibilites) to test the size of a dataGrid row.
 *
 * @author dev@testatoo.org
 */
public final class RowSize extends TypeSafeMatcher<DataGrid> {

    private int size;

    /**
     * Uses the matcher RowSize to compare the number of rows of a given dataGrid to the size of the matcher
     *
     * @param dataGrid the dataGrid
     * @return True if the number of rows of the dataGrid equals to the size of the matcher RowSize
     */
    @Override
    public boolean matchesSafely(DataGrid dataGrid) {
        return dataGrid.rows().size() == size;
    }

    /**
     * To append the description of the row size to the given description
     *
     * @param description the description may be part of a a description of a larger object
     */
    public void describeTo(Description description) {
        description.appendText("row size:" + size);
    }

    /**
     * Constructor with a given size
     *
     * @param size the size of the RowSize to create
     */
    public RowSize(int size) {
        this.size = size;
    }

    /**
     * To test the number of rows of a dataGrid using the syntax "is(n)" where n is the number of rows
     * Using the syntax "is(n)", the test creates a RowSize matcher with a size "n"
     *
     * @param size the size we want to compare with the number of the rows
     * @return a new RowSize matcher created with the given size
     */
    @Factory
    public static Matcher<DataGrid> is(int size) {
        return new RowSize(size);
    }
}
