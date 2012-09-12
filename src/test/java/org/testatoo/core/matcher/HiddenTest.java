package org.testatoo.core.matcher;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.core.matcher.Matchers.hidden;
import static org.testatoo.core.matcher.mock.MockFactory.*;

/**
 * @author dev@testatoo.org
 */
public class HiddenTest {

    @Test
    public void test_hidden_matcher() {
        assertThat(invisibleComponent(), is(hidden()));
        assertThat(visibleComponent(), is(not(hidden())));


        try {
            assertThat(visibleComponent(), is(hidden()));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: is hidden:true but: was <class org.testatoo.core.component.Component with state : enabled:true, visible:true>"));
        }
    }

}
