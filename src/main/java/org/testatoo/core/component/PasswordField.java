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

import static org.testatoo.core.ComponentType.PasswordField;

/**
 * This class allows the testing of PasswordField properties.
 * A PasswordField is a specific textField to enter passwords.
 *
 * @author dev@testatoo.org
 */
public class PasswordField extends AbstractTextField {

    /**
     * Class constructor specifying the evaluator to use and the id of the passwordField we want to test.
     * The constructor checks if the given id does correspond to a passwordField.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the passwordField
     */
    public PasswordField(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(PasswordField);
    }
}
