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

package org.testatoo.core.nature;

/**
 * This interface is a marking interface for all graphics objects that can be checked.
 *
 * @author dev@testatoo.org
 */
public interface Checkable {

    /**
     * To check the checkable object
     */
    void check();

    /**
     * To know if the checkable object is checked
     *
     * @return True if the checkable object is checked
     */
    Boolean isChecked();
}
