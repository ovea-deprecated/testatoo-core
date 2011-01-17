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

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.core.Language.cells;
import static org.testatoo.core.matcher.Matchers.has;
import static org.testatoo.core.matcher.mock.MockFactory.dataGrid;
import static org.testatoo.core.matcher.mock.MockFactory.format;

public class CelleSizeTest {

    @Test
    public void test_datagrid_column_cell_size() {
        assertThat(dataGrid().column(1), has(2, cells()));

        try {
            assertThat(dataGrid().column(1), has(5, cells()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: cell size:5 but: was <class org.testatoo.core.component.datagrid.Column with 2 cell(s) and title:Column 1>"));
        }
    }

}
