/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.atomic;

import org.assertj.core.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.AtomicShouldContain.shouldContain;
import static org.assertj.core.util.FailureMessages.actualIsNull;


public class AtomicBoolean_hasValue_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void should_fail_when_atomicBoolean_is_null() throws Exception {
    thrown.expectAssertionError(actualIsNull());

    assertThat((AtomicBoolean) null).hasValue(true);
  }

  @Test
  public void should_fail_if_expected_value_is_null() throws Exception {
    thrown.expectIllegalArgumentException("The expected value should not be <null>.");

    assertThat(new AtomicBoolean(true)).hasValue(null);
  }

  @Test
  public void should_fail_if_atomicBoolean_does_not_contain_expected_value() throws Exception {
    AtomicBoolean actual = new AtomicBoolean(true);
    boolean expectedValue = false;

    thrown.expectAssertionError(shouldContain(actual.get(), expectedValue).create());

    assertThat(actual).hasValue(expectedValue);
  }

  @Test
  public void should_pass_if_atomicBoolean_contains_expected_value() throws Exception {
    assertThat(new AtomicBoolean(true)).hasValue(true);
  }

}