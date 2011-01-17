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

import static org.testatoo.core.ComponentType.AlertBox;

/**
 * This class allows the testing of AlertBox properties.
 * The AlertBox is a special window that appears to display information to the user, with a single button to close it.
 *
 * @author dev@testatoo.org
 */
public class AlertBox extends AbstractWindow {

    /**
     * Class constructor specifying the evaluator to use and the id of the alertBox we want to test.
     * The constructor checks if the given id does correspond to an alertBox.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the alertBox
     */
    public AlertBox(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(AlertBox);
    }

    /**
     * To get the message displayed in the alertBox
     *
     * @return the message displayed in the alertBox
     */
    public String message() {
        return evaluator.message(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", message:" + message();
    }
}
