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
import static org.testatoo.core.Language.columns;
import static org.testatoo.core.matcher.Matchers.has;
import static org.testatoo.core.matcher.mock.MockFactory.*;

public class ColumnSizeTest {

    @Test
    public void test_datagrid_column_size() {
        assertThat(dataGrid(), has(3, columns()));

        try {
            assertThat(dataGrid(), has(5, columns()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: column size:5 but: was <class org.testatoo.core.component.datagrid.DataGrid with state : enabled:true, visible:true, column(s):3, row(s):1>"));
        }
    }

}
