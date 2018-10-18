package pl.malczuuu.problem4j.spring;

import java.io.Serializable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class ValidationDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String target;
  private final String error;

  public ValidationDetail(String target, String error) {
    this.target = target;
    this.error = error;
  }

  public String getTarget() {
    return target;
  }

  public String getError() {
    return error;
  }
}
