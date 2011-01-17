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

import static org.testatoo.core.ComponentType.Row;

/**
 * This class allows the testing of dataGrid row properties.
 *
 * @author dev@testatoo.org
 */
public class Row extends Component implements CellContainer {

    /**
     * Class constructor specifying the evaluator to use and the id of the component we want to test.
     * The constructor checks if the given id does correspond to a graphic component.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the component
     * @throws org.testatoo.core.ComponentException
     *          if the given id does not correspond to a component
     */
    public Row(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(Row);
    }

    /**
     * @see org.testatoo.core.component.datagrid.CellContainer
     */
    public Selection<Cell> cells() {
        return evaluator.cells(this);
    }


    /**
     * @see org.testatoo.core.component.datagrid.CellContainer
     */
    public Cell cell(int position) {
        try {
            return cells().get(position - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ComponentException("Cell number " + position + " is not available", e);
        }
    }

    /**
     * @see org.testatoo.core.component.Component
     */
    @Override
    public String toString() {
        return this.getClass().toString() + " with " + cells().size() + " cell(s)";
    }
}
