package io.github.malczuuu.problem4j.spring.web;

import java.io.Serializable;

public final class ValidationError implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String target;
  private final String error;

  public ValidationError(String target, String error) {
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