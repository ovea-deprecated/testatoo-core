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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.Evaluator;
import org.testatoo.core.EvaluatorHolder;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.testatoo.core.Language.assertThat;
import static org.testatoo.core.matcher.Matchers.exist;
import static org.testatoo.core.matcher.mock.MockFactory.*;

/**
 * @author dev@testatoo.org
 */
public class ExistTest {

    private Evaluator evaluator;

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.name()).thenReturn(Evaluator.DEFAULT_NAME);
        EvaluatorHolder.register(evaluator);
    }

    @After
    public void clean() {
        EvaluatorHolder.unregister(Evaluator.DEFAULT_NAME);
    }

    @Test
    public void test_exist_matcher_when_component_exist() {
        assertThat(existentComponent(evaluator), exist());
    }

    @Test
    public void test_exist_matcher_when_component_not_exist() {
        assertThat(inExistentComponent(evaluator), not(exist()));

        try {
            assertThat(inExistentComponent(evaluator), exist());
            fail();
        } catch (AssertionError e) {
            assertThat(format(e.getMessage()), is("Expected: exist:true but: was <class org.testatoo.core.component.Component with state : enabled:false, visible:false>"));
        }
    }
}
