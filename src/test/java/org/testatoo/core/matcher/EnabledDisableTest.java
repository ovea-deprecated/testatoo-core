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
import static org.testatoo.core.matcher.Matchers.disabled;
import static org.testatoo.core.matcher.Matchers.enabled;
import static org.testatoo.core.matcher.mock.MockFactory.disabledComponent;
import static org.testatoo.core.matcher.mock.MockFactory.enabledComponent;
import static org.testatoo.core.matcher.mock.MockFactory.format;

public class EnabledDisableTest {

    @Test
    public void test_enability_and_disability_matcher() {
        assertThat(enabledComponent(), is(enabled()));
        assertThat(enabledComponent(), is(not(disabled())));

        assertThat(disabledComponent(), is(not(enabled())));
        assertThat(disabledComponent(), is(disabled()));

        try {
            assertThat(disabledComponent(), is(enabled()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: is enabled:true but: was <class org.testatoo.core.component.Component with state : enabled:false, visible:true>"));
        }

        try {
            assertThat(enabledComponent(), is(disabled()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: is enabled:false but: was <class org.testatoo.core.component.Component with state : enabled:true, visible:true>"));
        }
    }
}
