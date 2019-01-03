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
package org.assertj.core.api;

import java.util.function.DoubleFunction;

/**
 * Assertion methods for {@link DoubleFunction DoubleFunction's}.
 * <p>
 * To create a new instance of this class, invoke <code>{@link Assertions#assertThat(Class)}</code>
 * </p>
 *
 * @param <R> result type of the {@link DoubleFunction}
 *
 * @author Evgeniy Tolmach
 */
public class DoubleFunctionAssert<R> extends
    AbstractFunctionLikeAssert<DoubleFunctionAssert<R>, DoubleFunction<R>, Double, R> {

  protected DoubleFunctionAssert(DoubleFunction<R> function) {
    super(function, i -> function.apply(i), DoubleFunctionAssert.class);
  }

  /**
   * Start lazy assertion chain
   *
   * @param input the input parameter to be used asserting that the {@link DoubleFunction} returns expected value
   * @return lazy assertion chain expecting to be followed by {@link DoubleFunctionAssert.On#returns(Object) returns(R)}
   */
  public On on(double input) {
    return new On(this, input);
  }

  /**
   * Lazy assertion chain. Holds the {@code FunctionAssert<T, R>} and the {@code input} value.
   * <p>The main goal of this class is to keep lazy assertion chain consistent.</p>
   *
   * @param <R> input parameter type of the function
   *
   * @author Evgeniy Tolmach
   */
  public static class On<R> extends
      AbstractFunctionLikeAssert.On<DoubleFunctionAssert<R>, DoubleFunction<R>, Double, R> {

    protected On(DoubleFunctionAssert<R> assertion, double input) {
      super(assertion, input);
    }

    /**
     * Verifies that {@link DoubleFunction} evaluates the given {@code input} to {@code expectedResult}.
     * <p>Example:</p>
     * <pre><code class='java'>
     * DoubleFunction<String> doubleFunction = l -> l.toString();
     * // this assertion passes:
     * assertThat(doubleFunction).on(10.21).returns("10.21");
     * // this assertion fails:
     * assertThat(doubleFunction).on(11.21).returns("10.21");
     * </code></pre>
     *
     * @return {@code this} assertions object
     * @throws AssertionError if {@code function} is {@code null}.
     * @throws AssertionError if the actual {@code Function} does not return {@code expectedResult}.
     */
    public DoubleFunctionAssert returns(R result) {
      return super.returns(result);
    }

  }

}
