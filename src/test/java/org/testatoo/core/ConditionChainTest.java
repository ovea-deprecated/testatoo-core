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

package org.testatoo.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConditionChainTest {

    @Test
    public void can_add_condition_to_the_chain() {
        ConditionChain chain = ConditionChain.create();
        chain.addCondition(new SuccessCondition());
        chain.addCondition(new SuccessCondition());

        assertThat(chain.conditions().size(), is(2));

        chain.addCondition(new SuccessCondition());

        assertThat(chain.conditions().size(), is(3));
    }

    @Test
    public void can_remove_condition_to_the_chain() {
        ConditionChain chain = ConditionChain.create();

        Condition condition1 = new SuccessCondition();
        Condition condition2 = new SuccessCondition();

        chain.addCondition(condition1);
        chain.addCondition(condition2);

        assertThat(chain.conditions().size(), is(2));

        chain.removeCondition(condition1);

        assertThat(chain.conditions().size(), is(1));
        assertThat(chain.conditions(), not(hasItem(condition1)));
        assertThat(chain.conditions(), hasItem(condition2));

        chain.removeCondition(condition2);

        assertThat(chain.conditions().size(), is(0));
    }

    @Test
    public void condition_chain_success_if_all_of_this_conditions_success() {
        ConditionChain chain = ConditionChain.create();

        Condition condition1 = new SuccessCondition();
        Condition condition2 = new SuccessCondition();

        chain.addCondition(condition1);
        chain.addCondition(condition2);

        assertThat(chain.isReach(), is(true));
    }

    @Test
    public void condition_chain_fail_if_one_of_this_conditions_fail() {
        ConditionChain chain = ConditionChain.create();

        Condition condition1 = new SuccessCondition();
        Condition condition2 = new FailCondition();

        chain.addCondition(condition1);
        chain.addCondition(condition2);

        assertThat(chain.isReach(), is(false));
    }

    private class DummyEvaluator extends EvaluatorSkeleton {
        @Override
        public Object implementation() {
            return null;
        }
    }
}
