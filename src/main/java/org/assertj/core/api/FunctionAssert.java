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

import java.util.function.Function;

/**
 * Assertion methods for {@link Function Function's}.
 * <p>
 * To create a new instance of this class, invoke <code>{@link org.assertj.core.api.Assertions#assertThat(Class)}</code>
 * </p>
 * 
 * @param <T> input parameter type of the function
 * @param <R> result type of the function
 *
 * @author Evgeniy Tolmach
 */
public class FunctionAssert<T, R> extends AbstractFunctionLikeAssert<FunctionAssert<T, R>, Function<T, R>, T, R> {

  protected FunctionAssert(Function<T, R> function) {
    super(function, function, FunctionAssert.class);
  }

  /**
   * Start lazy assertion chain
   *
   * @param input the input parameter to be used asserting that the {@link Function} returns expected value
   * @return lazy assertion chain expecting to be followed by {@link FunctionAssert.On#returns(Object) returns(R)}
   */
  public FunctionAssert.On<T, R> on(T input) {
    return new FunctionAssert.On<>(this, input);
  }

  /**
   * Lazy assertion chain. Holds the {@code FunctionAssert<T, R>} and the {@code input} value.
   * <p>The main goal of this class is to keep lazy assertion chain consistent.</p>
   *
   * @param <T> input parameter type of the function
   * @param <R> result type of the function
   *
   * @author Evgeniy Tolmach
   */
  public static class On<T, R> extends AbstractFunctionLikeAssert.On<FunctionAssert<T, R>, Function<T, R>, T, R> {

    protected On(FunctionAssert<T, R> assertion, T input) {
      super(assertion, input);
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
    @Override
    public FunctionAssert<T, R> returns(R expectedResult) {
      return super.returns(expectedResult);
    }
  }

}
