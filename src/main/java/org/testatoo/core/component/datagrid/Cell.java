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

import org.testatoo.core.Evaluator;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.ValueSupport;

import static org.testatoo.core.ComponentType.Cell;

/**
 * This class allows the testing of dataGrid cell properties.
 *
 * @author dev@testatoo.org
 */
public class Cell extends Component implements ValueSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the component we want to test.
     * The constructor checks if the given id does correspond to a graphic component.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the component
     * @throws org.testatoo.core.ComponentException
     *          if the given id does not correspond to a component
     */
    public Cell(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(Cell);
    }

    /**
     * @see org.testatoo.core.nature.ValueSupport
     */
    public String value() {
        return evaluator.value(this);
    }

    /**
     * @see org.testatoo.core.component.Component
     */
    @Override
    public String toString() {
        return this.getClass().toString() + " with value:" + value();
    }
}
