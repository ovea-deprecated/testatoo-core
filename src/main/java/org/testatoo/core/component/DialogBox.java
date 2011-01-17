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

import static org.testatoo.core.ComponentType.DialogBox;

/**
 * This class allows the testing of DialogBox properties.
 * The DialogBox is a special window that appears to display information or ask question to the user.
 * There is at least 2 buttons in the DialogBox (if one button : it's an AlertBox).
 *
 * @author dev@testatoo.org
 */
public class DialogBox extends AbstractWindow {

    /**
     * Class constructor specifying the evaluator to use and the id of the dialogBox we want to test.
     * The constructor checks if the given id does correspond to a dialogBox.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the dialogBox
     */
    public DialogBox(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(DialogBox);
    }

    /**
     * To get the message displayed in the dialogBox
     *
     * @return the message displayed in the dialogBox
     */
    public String message() {
        return evaluator.message(this);
    }

    /**
     * To get the buttons contained in the dialogBox
     *
     * @return the list of Button objects
     */
    public Selection<Button> buttons() {
        return evaluator.buttons(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", message:" + message();
    }
}
