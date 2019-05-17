package io.github.malczuuu.problem4j.spring.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public final class ValidationError implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String path;
  private final String error;

  @JsonCreator
  public ValidationError(@JsonProperty("path") String path, @JsonProperty("error") String error) {
    this.path = path;
    this.error = error;
  }

  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  @JsonProperty("error")
  public String getError() {
    return error;
  }
}
