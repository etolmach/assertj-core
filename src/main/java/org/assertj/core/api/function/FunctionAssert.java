package org.assertj.core.api.function;

import org.assertj.core.api.AbstractFunctionLikeAssert;

import java.util.function.Function;

/**
 * @author Evgeniy Tolmach
 */
public class FunctionAssert<T, R> extends AbstractFunctionLikeAssert<FunctionAssert<T, R>, Function<T, R>, T, R> {

  public FunctionAssert(Function<T, R> function) {
    super(function, function, FunctionAssert.class);
  }

  public FunctionAssert.On<T, R> on(T input) {
    return new FunctionAssert.On<>(this, input);
  }

  public static class On<T, R> extends AbstractFunctionLikeAssert.On<FunctionAssert<T, R>, Function<T, R>, T, R> {

    public On(FunctionAssert<T, R> assertion, T input) {
      super(assertion, input);
    }

    @Override
    public FunctionAssert<T, R> returns(R expectedResult) {
      return super.returns(expectedResult);
    }
  }
  
}
