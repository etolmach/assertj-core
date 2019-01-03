package org.assertj.core.api;

import static org.assertj.core.error.UnaryFunctionShouldReturn.shouldReturn;

import java.util.function.Function;

import org.assertj.core.util.VisibleForTesting;

/**
 * @author Evgeniy Tolmach
 */

public abstract class AbstractFunctionLikeAssert<SELF extends AbstractFunctionLikeAssert<SELF, FUNCTION, INPUT, RESULT>, FUNCTION, INPUT, RESULT> extends AbstractAssert<SELF, FUNCTION> {

  @VisibleForTesting
  final Function<INPUT, RESULT> function;

  protected AbstractFunctionLikeAssert(FUNCTION function, Function<INPUT, RESULT> wrappedFunction, Class<?> selfType) {
    super(function, selfType);
    this.function = wrappedFunction;
  }

  protected abstract static class On<SELF extends AbstractFunctionLikeAssert<SELF, FUNCTION, INPUT, RESULT>, FUNCTION, INPUT, RESULT> {

    @VisibleForTesting
    final SELF assertion;
    @VisibleForTesting
    final INPUT input;

    public On(SELF assertion, INPUT input) {
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
