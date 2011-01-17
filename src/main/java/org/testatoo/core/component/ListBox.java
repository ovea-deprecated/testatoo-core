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
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.MultiSelectable;

import static org.testatoo.core.ComponentType.ListBox;

/**
 * This class allows the testing of ListBox properties.
 * The ListBox allows the user to select one or more items from the displayed list.
 *
 * @author dev@testatoo.org
 */
public class ListBox extends ListModel implements MultiSelectable, LabelSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the listBox we want to test.
     * The constructor checks if the given id does correspond to a listBox.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the listBox
     */
    public ListBox(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(ListBox);
    }

    /**
     * @see org.testatoo.core.nature.MultiSelectable
     */
    public Selection<String> selectedValues() {
        return listSelectedValues();
    }

    /**
     * @see ListModel
     */
    @Override
    public void unSelect(String value) {
        super.unSelect(value);
    }

    /**
     * @see org.testatoo.core.nature.LabelSupport
     */
    public String label() {
        return evaluator.label(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", label:" + label();
    }
}
