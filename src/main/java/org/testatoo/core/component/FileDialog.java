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

import static org.testatoo.core.ComponentType.FileDialog;

/**
 * This class allows the testing of the FileDialog properties.
 * The FileDialog is a dialog box that allows users to choose a file from the file system.
 *
 * @author dev@testatoo.org
 */
public class FileDialog extends AbstractWindow {

    /**
     * Class constructor specifying the evaluator to use and the id of the fileDialog we want to test.
     * The constructor checks if the given id does correspond to a fileDialog.
     *
     * @param evaluator a technology specific evaluator
     * @param id        the id (unique) of the fileDialog
     */
    public FileDialog(Evaluator evaluator, String id) {
        super(evaluator, id);
        checkIsExpectedComponent(FileDialog);
    }

    /**
     * To set the complete name (including path) of the file
     *
     * @param filePath the complete name of the file
     */
    public void selectFilePath(String filePath) {
        evaluator.selectFilePath(filePath, this);
    }

    /**
     * To get the complete path towards the selected File
     *
     * @return the complete path towards the selected File
     */
    public String selectedFilePath() {
        return evaluator.selectedFilePath(this);
    }

    /**
     * @see Component
     */
    @Override
    public String toString() {
        return super.toString() + ", selectedFilePath:" + selectedFilePath();
    }
}
