package org.assertj.core.api;

import org.assertj.core.util.VisibleForTesting;

import java.util.function.Function;

import static org.assertj.core.error.UnaryFunctionShouldReturn.shouldReturn;

/**
 * @author Evgeniy Tolmach
 */

public abstract class AbstractUnaryFunctionLikeAssert<SELF extends AbstractUnaryFunctionLikeAssert<SELF, FUNCTION, PRIMITIVE, RESULT>, FUNCTION, PRIMITIVE, RESULT> extends AbstractAssert<SELF, FUNCTION> {

  @VisibleForTesting
  final Function<PRIMITIVE, RESULT> function;

  protected AbstractUnaryFunctionLikeAssert(FUNCTION function, Function<PRIMITIVE, RESULT> wrappedFunction, Class<?> selfType) {
    super(function, selfType);
    this.function = wrappedFunction;
  }

  protected abstract static class On<SELF extends AbstractUnaryFunctionLikeAssert<SELF, FUNCTION, PRIMITIVE, RESULT>, FUNCTION, PRIMITIVE, RESULT> {

    @VisibleForTesting
    final SELF assertion;
    @VisibleForTesting
    final PRIMITIVE input;

    public On(SELF assertion, PRIMITIVE input) {
      this.assertion = assertion;
      this.input = input;
    }

    protected SELF returns(RESULT expectedResult) {
      this.assertion.isNotNull();
      RESULT actualResult = this.assertion.function.apply(input);
      if (!actualResult.equals(expectedResult)) {
        this.assertion.throwAssertionError(shouldReturn(this.assertion.function, input, expectedResult, actualResult));
      }
      return this.assertion.myself;
    }

  }
}
