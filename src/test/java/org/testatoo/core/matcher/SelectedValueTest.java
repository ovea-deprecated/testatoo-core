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

package org.testatoo.core.matcher;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.testatoo.core.matcher.Matchers.*;
import static org.testatoo.core.matcher.mock.MockFactory.*;

/**
 * @author dev@testatoo.org
 */
public class SelectedValueTest {

    @Test
    public void test_selectedValue_matcher() {
        assertThat(comboBox(), has(selectedValue("Canada")));
        assertThat(comboBox(), has(not(selectedValue("Italy"))));

        try {
            assertThat(comboBox(), has(selectedValue("Spain")));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: has selected value:Spain but: was <class org.testatoo.core.component.ComboBox with state : enabled:true, visible:true, values:[France, Canada, Germany, Italy, Spain], selectedValues:[Canada], label:label>"));
        }
    }

}
