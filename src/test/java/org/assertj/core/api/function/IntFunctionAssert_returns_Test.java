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
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.api.function;

import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.util.function.IntFunction;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.IntFunctionAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link IntFunctionAssert.On#returns(Object)}.
 *
 * @author Evgeniy Tolmach
 */
public class IntFunctionAssert_returns_Test {

  public static final int INPUT_1 = 123;
  public static final int INPUT_2 = 456;

  public static final String RESULT_1 = "123";
  public static final String RESULT_2 = "456";

  public static final IntFunction<String> NULL_FUNCTION = null;
  public static final IntFunction<String> FUNCTION = Integer::toString;

  @Test
  public void should_fail_when_predicate_is_null() {
    Assertions.assertThatExceptionOfType(AssertionError.class)
              .isThrownBy(() -> Assertions.assertThat(NULL_FUNCTION)
                                          .on(INPUT_1)
                                          .returns(RESULT_1))
              .withMessage(actualIsNull());
  }

  @Test
  public void should_pass_when_valid_input_and_output() {
    Assertions.assertThat(FUNCTION).on(INPUT_1).returns(RESULT_1);
  }

  @Test
  public void should_pass_when_multiple_valid_inputs_and_outputs() {
    Assertions.assertThat(FUNCTION)
              .on(INPUT_1).returns(RESULT_1)
              .on(INPUT_2).returns(RESULT_2);
  }

  @Test
  public void should_fail_when_invalid_output() {
    Assertions.assertThatExceptionOfType(AssertionError.class)
              .isThrownBy(() -> Assertions.assertThat(FUNCTION)
                                          .on(INPUT_1).returns(RESULT_2));
  }

  @Test
  public void should_fail_when_invalid_output_after_a_valid_one() {
    Assertions.assertThatExceptionOfType(AssertionError.class)
              .isThrownBy(() -> Assertions.assertThat(FUNCTION)
                                          .on(INPUT_1).returns(RESULT_1)
                                          .on(INPUT_2).returns(RESULT_1));
  }

}
