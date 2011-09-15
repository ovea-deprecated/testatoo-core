package org.testatoo.core.matcher;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.testatoo.core.matcher.Matchers.collapsed;
import static org.testatoo.core.matcher.mock.MockFactory.*;

/**
 * @author David Avenante
 */
public class CollapsableTest {

    @Test
    public void test_callapsable_matcher() {
        assertThat(collapsedComponent(), is(collapsed()));
        assertThat(unCollapsedComponent(), is(not(collapsed())));

        try {
            assertThat(collapsedComponent(), is(not(collapsed())));
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: is not collapsed:true but: was <collapsed:true>"));
        }

    }

}
