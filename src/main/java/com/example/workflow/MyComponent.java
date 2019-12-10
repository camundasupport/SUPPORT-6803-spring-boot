/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

  @Autowired
  protected RuntimeService runtimeService;

  // wait for 30 seconds until engine has been started
  // create a new process instance each second
  @Scheduled(initialDelay = 30_000, fixedDelay = 1)
  public void bar() {
    runtimeService.startProcessInstanceByKey("process",
        Variables.createVariables().putValue("payload",
            Variables.serializedObjectValue("{\"foo\":\"bar\"}")
                .objectTypeName("java.util.LinkedHashMap<java.lang.Object,java.lang.Object>")
                .serializationDataFormat("application/json")
                .create()));
  }

}
