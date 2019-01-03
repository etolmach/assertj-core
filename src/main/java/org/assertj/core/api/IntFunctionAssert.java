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

import java.util.function.IntFunction;

/**
 * Assertion methods for {@link IntFunction IntFunction's}.
 * <p>
 * To create a new instance of this class, invoke <code>{@link org.assertj.core.api.Assertions#assertThat(Class)}</code>
 * </p>
 *
 * @param <R> result type of the {@link IntFunction}
 *
 * @author Evgeniy Tolmach
 */
public class IntFunctionAssert<R> extends AbstractFunctionLikeAssert<IntFunctionAssert<R>, IntFunction<R>, Integer, R> {

  protected IntFunctionAssert(IntFunction<R> function) {
    super(function, i -> function.apply(i), IntFunctionAssert.class);
  }

  /**
   * Start lazy assertion chain
   *
   * @param input the input parameter to be used asserting that the {@link IntFunction} returns expected value
   * @return lazy assertion chain expecting to be followed by {@link IntFunctionAssert.On#returns(Object) returns(R)}
   */
  public On on(int input) {
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
  public static class On<R> extends AbstractFunctionLikeAssert.On<IntFunctionAssert<R>, IntFunction<R>, Integer, R> {

    protected On(IntFunctionAssert<R> assertion, int input) {
      super(assertion, input);
    }

    /**
     * Verifies that {@link IntFunction} evaluates the given {@code input} to {@code expectedResult}.
     * <p>Example:</p>
     * <pre><code class='java'>
     * IntFunction<String> intFunction = i -> i.toString();
     * // this assertion passes:
     * assertThat(intFunction).on(10).returns("10");
     * // this assertion fails:
     * assertThat(intFunction).on(11).returns("10");
     * </code></pre>
     *
     * @return {@code this} assertions object
     * @throws AssertionError if {@code function} is {@code null}.
     * @throws AssertionError if the actual {@code Function} does not return {@code expectedResult}.
     */
    public IntFunctionAssert returns(R result) {
      return super.returns(result);
    }

  }

}
