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
import org.testatoo.core.nature.IconSupport;
import org.testatoo.core.nature.TextSupport;

import static org.testatoo.core.ComponentType.Button;

/**
 * This class allows the testing of button properties.
 *
 * @author dev@testatoo.org
 */
public class Button extends Component implements TextSupport, IconSupport {

    /**
     * Class constructor specifying the evaluator to use and the id of the button we want to test.
     * The constructor checks if the given id does correspond to a button.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the button
     */
    public Button(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(Button);
    }

    /**
     * To get the text displayed on the button
     *
     * @return the text displayed on the button
     */
    public String text() {
        return evaluator.text(this);
    }

    /**
     * To get information about the icon displayed on the button
     *
     * @return the information about the icon displayed on the button
     */
    public String icon() {
        return evaluator.icon(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", text:" + text() + ", icon:" + icon();
    }
}
