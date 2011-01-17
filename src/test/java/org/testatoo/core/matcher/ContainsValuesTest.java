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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.core.matcher.Matchers.containsExactlyValues;
import static org.testatoo.core.matcher.Matchers.containsValues;
import static org.testatoo.core.matcher.mock.MockFactory.comboBox;
import static org.testatoo.core.matcher.mock.MockFactory.format;

public class ContainsValuesTest {

    @Test
    public void test_containValues_matcher() {
        assertThat(comboBox(), containsValues("Italy", "Spain", "Canada"));
        assertThat(comboBox(), not(containsValues("France", "USA", "Cambodgia")));

        try {
            assertThat(comboBox(), containsValues("France", "Cambodgia"));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: contains value(s):[France, Cambodgia] got: <class org.testatoo.core.component.ComboBox with state : enabled:true, visible:true, values:[France, Canada, Germany, Italy, Spain], selectedValues:[Canada], label:label>"));
        }
    }

    @Test
    public void test_containsExactlyValues_matcher() {
        assertThat(comboBox(), containsExactlyValues("France", "Canada", "Germany", "Italy", "Spain"));
        assertThat(comboBox(), not(containsExactlyValues("Italy", "Spain", "Canada")));

        try {
            assertThat(comboBox(), containsExactlyValues("Italy", "Spain", "Canada"));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: contains exactly value(s):[Italy, Spain, Canada] got: <class org.testatoo.core.component.ComboBox with state : enabled:true, visible:true, values:[France, Canada, Germany, Italy, Spain], selectedValues:[Canada], label:label>"));
        }
    }
}
