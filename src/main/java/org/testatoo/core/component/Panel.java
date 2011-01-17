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

import static org.testatoo.core.ComponentType.Panel;

/**
 * This class allows the testing of Panel properties.
 *
 * @author dev@testatoo.org
 */
public class Panel extends Component implements TitleSupport, Container {

    /**
     * Class constructor specifying the evaluator to use and the id of the panel we want to test.
     * The constructor checks if the given id does correspond to a panel.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the panel
     */
    public Panel(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(Panel);
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
