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
import org.testatoo.core.nature.Container;
import org.testatoo.core.nature.TitleSupport;


/**
 * This is the abstract base class for all graphic Windows (ex : AlertBox, FileDialog, etc.).
 * To test basic graphic window properties, subClasses will call methods of this class.
 *
 * @author dev@testatoo.org
 */
public abstract class AbstractWindow extends Component implements TitleSupport, Container {

    /**
     * Class constructor specifying the evaluator to use and the id of the window
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the window
     */
    public AbstractWindow(Evaluator evaluator, String id) {
        super(evaluator, id);
    }

    /**
     * To launch the action of closing the window.
     */
    public void close() {
        evaluator.close(this);
    }

    /**
     * @see org.testatoo.core.nature.TitleSupport
     */
    public String title() {
        return evaluator.title(this);
    }

    /**
     * @see org.testatoo.core.nature.Container
     */
    public Boolean contains(Component... component) {
        return evaluator.contains(this, component);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", title:" + title();
    }
}
