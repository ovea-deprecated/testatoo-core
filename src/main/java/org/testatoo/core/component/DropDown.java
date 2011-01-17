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
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.SimpleSelectable;

import static org.testatoo.core.ComponentType.DropDown;

/**
 * This class allows the testing of a DropDown properties.
 * The DropDown allows the user to choose one item from a list.
 * When the dropDown list is inactive, it displays a single item.
 * When activated, it displays a list of items, from which the user may select one.
 * When the user selects a new item, the control reverts to its inactive state, displaying the selected item.
 *
 * @author dev@testatoo.org
 */
public class DropDown extends ListModel implements SimpleSelectable, LabelSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the dropDown we want to test.
     * The constructor checks if the given id does correspond to a dropDown.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the dropDown
     */
    public DropDown(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(DropDown);
    }

    /**
     * @see org.testatoo.core.nature.SimpleSelectable
     */
    public String selectedValue() {
        if (listSelectedValues().size() == 0) {
            return "";
        }
        return listSelectedValues().get(0);
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
