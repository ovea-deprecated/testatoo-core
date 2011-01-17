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

package org.testatoo.core.component;

import org.testatoo.core.Evaluator;
import org.testatoo.core.Selection;

/**
 * This is the abstract base class for all type of lists (ex : DropDown, ListBox).
 * To test ListModel properties, subClasses will call methods of this class.
 *
 * @author dev@testatoo.org
 */
public abstract class ListModel extends Component {

    /**
     * Class constructor specifying the evaluator to use and the id of the listModel
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the listModel
     */
    public ListModel(Evaluator evaluator, String id) {
        super(evaluator, id);
    }

    /**
     * To get the values of the list
     *
     * @return the values of the list
     */
    public Selection<String> values() {
        return evaluator.values(this);
    }

    /**
     * To select a given value in the list
     *
     * @param value the value we want to be selected in the list
     */
    public void select(String value) {
        if (values().contains(value)) {
            evaluator.select(value, this);
        }
    }

    /**
     * To unselect a given value in the list
     *
     * @param value the value we want to be unselected in the list
     */
    protected void unSelect(String value) {
        if (values().contains(value)) {
            evaluator.unselect(value, this);
        }
    }

    /**
     * To get the selected values in the list
     *
     * @return the selected values in the list
     */
    protected Selection<String> listSelectedValues() {
        return evaluator.selectedValues(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", values:" + values()
                + ", selectedValues:" + listSelectedValues();
    }
}
