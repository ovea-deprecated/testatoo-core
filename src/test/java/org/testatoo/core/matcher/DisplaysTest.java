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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.core.Language.assertThat;
import static org.testatoo.core.matcher.Matchers.displays;
import static org.testatoo.core.matcher.mock.MockFactory.*;

public class DisplaysTest {

    @Test
    public void test_display_matcher() {
        assertThat(containsAllContainer(), displays(visibleComponent()));
        assertThat(containsAllContainer(), displays(visibleComponent(), visibleComponent()));

        try {
            assertThat(containsNothingContainer(), displays(visibleComponent()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: contains all of {<class org.testatoo.core.component.Component with state : enabled:true, visible:true>} but: was <contains all: false>"));
        }

        try {
            assertThat(containsAllContainer(), displays(invisibleComponent()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: all of this must be visible {<class org.testatoo.core.component.Component with state : enabled:true, visible:false>} but: was <contains all: true>"));
        }

        try {
            assertThat(containsNothingContainer(), displays(visibleComponent(), visibleComponent()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: contains all of {<class org.testatoo.core.component.Component with state : enabled:true, visible:true>, <class org.testatoo.core.component.Component with state : enabled:true, visible:true>} but: was <contains all: false>"));
        }
    }
}
