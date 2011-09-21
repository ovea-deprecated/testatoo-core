package org.testatoo.core.matcher;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.testatoo.core.Language.assertThat;
import static org.testatoo.core.matcher.Displays.displays;
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
