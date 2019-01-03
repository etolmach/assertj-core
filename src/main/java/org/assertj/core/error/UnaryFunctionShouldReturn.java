/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.core.error;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Evgeniy Tolmach
 */
public class UnaryFunctionShouldReturn extends BasicErrorMessageFactory {

  public static <T, R> ErrorMessageFactory shouldReturn(Function<T, R> function, T input, R expectedResult, R actualResult) {
    Objects.requireNonNull("The description must not be null.");
    return new UnaryFunctionShouldReturn(function.getClass().getSimpleName(), input, expectedResult, actualResult);
  }

  private UnaryFunctionShouldReturn(String description, Object input, Object expectedResult, Object actualResult) {
    super("%nExpecting:%n  <given %s>%nto return <%s> on <%s> but actual result was <%s>.", description.toString(), input.toString(), expectedResult.toString(), actualResult.toString());
  }

}
