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
