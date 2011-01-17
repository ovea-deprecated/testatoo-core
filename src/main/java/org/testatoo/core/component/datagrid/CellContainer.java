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

package org.testatoo.core.component.datagrid;

import org.testatoo.core.Selection;

/**
 * This interface is used for rows and columns of the dataGrid (both containing cells)
 *
 * @author dev@testatoo.org
 */
public interface CellContainer {

    /**
     * To get the list of cells of the container
     *
     * @return the list of Cell objects contained in the container
     */
    Selection<Cell> cells();

    /**
     * To get the cell at a given position in a container
     *
     * @param position the position of the cell in the container
     * @return the cell at the given position
     */
    Cell cell(int position);
}
