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
import org.testatoo.core.nature.Checkable;
import org.testatoo.core.nature.LabelSupport;

import static org.testatoo.core.ComponentType.CheckBox;

/**
 * This class allows the testing of checkBox properties.
 *
 * @author dev@testatoo.org
 */
public class CheckBox extends Component implements Checkable, LabelSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the checkBox we want to test.
     * The checkBox allows the user to make multiple selections.
     * The constructor checks if the given id does correspond to a checkBox.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the checkBox
     */
    public CheckBox(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(CheckBox);
    }

    /**
     * @see org.testatoo.core.nature.Checkable
     */
    public void check() {
        evaluator.check(this);
    }

    /**
     * @see org.testatoo.core.nature.Checkable
     */
    public void unCheck() {
        evaluator.unCheck(this);
    }

    /**
     * @see org.testatoo.core.nature.Checkable
     */
    public Boolean isChecked() {
        return evaluator.isChecked(this);
    }

    /**
     * @see org.testatoo.core.nature.LabelSupport
     */
    @Override
    public String label() {
        return evaluator.label(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", label:" + label() + ", checked:" + isChecked();
    }
}
