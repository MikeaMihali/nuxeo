/*
 * (C) Copyright 2011 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     ldoguin
 */
package org.nuxeo.ecm.platform.routing.dm.api;

/**
 * @deprecated since 5.9.2 - Use only routes of type 'graph'
 */
@Deprecated
public class RoutingTaskConstants {

    public static final String TASK_STEP_FACET_NAME = "TaskStep";

    public static final String TASK_STEP_ACTORS_PROPERTY_NAME = "tkst:actors";

    public static final String TASK_STEP_DUE_DATE_PROPERTY_NAME = "tkst:dueDate";

    public static final String TASK_STEP_AUTOMATIC_VALIDATION_PROPERTY_NAME = "tkst:automaticValidation";

    public static final String TASK_STEP_DIRECTIVE_PROPERTY_NAME = "tkst:directive";

    public static final String TASK_STEP_COMMENTS_PROPERTY_NAME = "tkst:comments";

    public static final String ROUTING_TASK_ACTORS_KEY = "document.routing.task.actors";

    public enum EvaluationOperators {
        equal, not_equal, less_than, less_or_equal_than, greater_than, greater_or_equal_than
    }

}