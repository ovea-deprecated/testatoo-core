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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConditionChain implements Condition {

    private List<Condition> conditions = new ArrayList<Condition>();

    private ConditionChain() {
    }

    public static ConditionChain create() {
        return new ConditionChain();
    }

    public boolean addCondition(Condition condition) {
        return conditions.add(condition);
    }

    public List<Condition> conditions() {
        return Collections.unmodifiableList(conditions);
    }

    public boolean removeCondition(Condition condition) {
        return conditions.remove(condition);
    }

    public boolean isReach() {
        for (Condition condition : conditions) {
            if (!condition.isReach())
                return false;
        }
        return true;
    }
}
