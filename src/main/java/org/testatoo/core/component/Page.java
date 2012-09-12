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
 * This class allows the testing of page.
 *
 * @author dev@testatoo.org
 */
public final class Page extends Component implements Container, TitleSupport {

    private Evaluator evaluator;

    /**
     * Class constructor specifying the evaluator to use and the id of the page we want to test.
     *
     * @param evaluator a UI Test engine specific html evaluator
     * @param id        the id (unique) of the page
     */
    public Page(Evaluator evaluator, String id) {
        super(evaluator, id);
        this.evaluator = evaluator;
    }

    /**
     * To get the title of the page.
     *
     * @return the title in the page
     */
    public String title() {
        return evaluator.title(this);
    }

    /**
     * To know if the page contains all the given components.
     * Placeholder for language :
     * 1 - if the component doesn't exit a component Exception is thrown before the call of this method
     * 2 - If this method is called, it means that all the components exists and so there are contained by the current page.
     *
     * @return true
     */
    public Boolean contains(Component... component) {
        return true;
    }

    /**
     * To know if the page is Enabled.
     *
     * @return true because we have loaded the page
     */
    @Override
    public Boolean isEnabled() {
        return true;
    }

    /**
     * To know if the page is visible.
     *
     * @return true because we have loaded the page
     */
    @Override
    public Boolean isVisible() {
        return true;
    }

    /**
     * To open the page.
     *
     * @param url the url corresponding to the html page we want to open
     */
    public Page open(String url) {
        evaluator.open(url);
        return this;
    }

    /**
     * To get the source corresponding to the page.
     *
     * @return the page source
     */
    public String source() {
        return evaluator.pageSource();
    }

    /**
     * To get the string describing the page.
     *
     * @return string describing the page
     */
    public String toString() {
        return super.toString() + ", title:" + title();
    }

}
