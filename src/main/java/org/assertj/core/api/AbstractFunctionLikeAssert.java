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
package org.assertj.core.api;

import static org.assertj.core.error.UnaryFunctionShouldReturn.shouldReturn;

import java.util.Objects;
import java.util.function.Function;

/**
 * Base class for all implementations of assertions for {@link java.util.function.Function Functions}
 * and {@link java.util.function.UnaryOperator UnaryOperators} (including the primitive ones).
 *
 * @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <FUNCTION> type of the function to verify
 * @param <INPUT> input parameter type of the function
 * @param <RESULT> result type of the function
 *
 * @author Evgeniy Tolmach
 */
public abstract class AbstractFunctionLikeAssert<SELF extends AbstractFunctionLikeAssert<SELF, FUNCTION, INPUT, RESULT>, FUNCTION, INPUT, RESULT>
    extends AbstractAssert<SELF, FUNCTION> {

  protected final Function<INPUT, RESULT> function;

  /**
   * @param function the actual function to test
   * @param wrappedFunction the actual function wrapped into non-primitive {@link Function}.
   * @param selfType
   */
  protected AbstractFunctionLikeAssert(FUNCTION function, Function<INPUT, RESULT> wrappedFunction, Class<?> selfType) {
    super(function, selfType);
    this.function = wrappedFunction;
  }

  /**
   * Lazy assertion chain. Holds the {@code SELF} and the {@code input} value.
   * <p>The main goal of this class is to keep lazy assertion chain consistent.</p>
   *
   * @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
   *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
   *          for more details.
   * @param <FUNCTION> type of the function to verify
   * @param <INPUT> input parameter type of the function
   * @param <RESULT> result type of the function
   *
   * @author Evgeniy Tolmach
   */
  protected abstract static class On<SELF extends AbstractFunctionLikeAssert<SELF, FUNCTION, INPUT, RESULT>, FUNCTION, INPUT, RESULT> {

    protected final SELF assertion;
    protected final INPUT input;

    /**
     * The constructor is protected in order to prevent inappropriate usages of this class.
     *
     * @param assertion initial assertion to chain with
     * @param input the input parameter to be used asserting that the {@link Function} returns expected value
     */
    protected On(SELF assertion, INPUT input) {
      this.assertion = assertion;
      this.input = input;
    }

    /**
     * Verifies that {@link Function} evaluates the given {@code input} to {@code expectedResult}.
     * <p>Example:</p>
     * <pre><code class='java'>
     * Function<Integer, String> function = i -> i.toString();
     * // this assertion passes:
     * assertThat(function).on(10).returns("10");
     * // this assertion fails:
     * assertThat(function).on(11).returns("10");
     * </code></pre>
     *
     * @return {@code this} assertions object
     * @throws AssertionError if {@code function} is {@code null}.
     * @throws AssertionError if the actual {@code Function} does not return {@code expectedResult}.
     */
    protected SELF returns(RESULT expectedResult) {
      this.assertion.isNotNull();
      RESULT actualResult = this.assertion.function.apply(input);
      if (!Objects.equals(actualResult, expectedResult)) {
        this.assertion.throwAssertionError(shouldReturn(this.assertion.function, input, expectedResult, actualResult));
      }
      return this.assertion.myself;
    }

  }

}
