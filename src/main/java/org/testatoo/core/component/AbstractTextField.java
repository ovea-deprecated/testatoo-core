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
import org.testatoo.core.nature.TextSupport;

/**
 * This is the abstract base class for all type of textFields (ex : passwordField).
 * To test textField properties, subClasses will call methods of this class.
 *
 * @author dev@testatoo.org
 */
public abstract class AbstractTextField extends Field implements TextSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the textField
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the textField
     */
    public AbstractTextField(Evaluator evaluator, String id) {
        super(evaluator, id);
    }

    /**
     * To get the maximum length of the text entered in the textField
     *
     * @return maximum length of the text entered in the textField
     */
    public int maxLength() {
        return evaluator.maxLength(this);
    }

    /**
     * To get the text contained in the textfield
     *
     * @return the text contained in the textfield
     */
    @Override
    public String text() {
        return value();
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        if (Integer.valueOf(maxLength()).equals(Integer.MAX_VALUE)) {
            return super.toString();
        }
        return super.toString() + ", maxLength:" + maxLength();
    }
}
