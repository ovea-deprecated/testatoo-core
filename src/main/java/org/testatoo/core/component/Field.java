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
import org.testatoo.core.nature.ValueSupport;


/**
 * This is the abstract base class for all type of field (ex : TextField, NumberField).
 * To test Field properties, subClasses will call methods of this class.
 *
 * @author dev@testatoo.org
 */
public abstract class Field extends Component implements LabelSupport, ValueSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the field we want to test.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the field
     */
    public Field(Evaluator evaluator, String id) {
        super(evaluator, id);
    }

    /**
     * @see org.testatoo.core.nature.LabelSupport
     */
    public String label() {
        return evaluator.label(this);
    }

    /**
     * @see org.testatoo.core.nature.ValueSupport
     */
    public String value() {
        return evaluator.value(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", value:" + value() + ", label:" + label();
    }
}
