package io.github.malczuuu.problem4j.core;

import java.net.URI;
import java.util.Map;

public class ProblemException extends RuntimeException {

  private final Problem problem;

  public ProblemException(
      URI type,
      String title,
      int status,
      String detail,
      URI instance,
      Map<String, Object> extensions) {
    this(constructProblem(type, title, status, detail, instance, extensions));
  }

  private static Problem constructProblem(
      URI type,
      String title,
      Integer status,
      String detail,
      URI instance,
      Map<String, Object> extensions) {
    ProblemBuilder builder = Problem.builder();
    if (type != null) {
      builder = builder.type(type);
    }
    if (title != null) {
      builder = builder.title(title);
    }
    if (status != null) {
      builder = builder.status(status);
    }
    if (detail != null) {
      builder = builder.detail(detail);
    }
    if (instance != null) {
      builder = builder.instance(instance);
    }
    if (extensions != null) {
      for (String key : extensions.keySet()) {
        builder = builder.extension(key, extensions.get(key));
      }
    }
    return builder.build();
  }

  public ProblemException(URI type, String title, int status, String detail, URI instance) {
    this(type, title, status, detail, instance, (Map<String, Object>) null);
  }

  public ProblemException(URI type, String title, int status, String detail) {
    this(type, title, status, detail, null, (Map<String, Object>) null);
  }

  public ProblemException(URI type, String title, int status) {
    this(type, title, status, null, null, (Map<String, Object>) null);
  }

  public ProblemException(Problem problem) {
    super(emptyIfNull(problem.getDetail()));
    this.problem = problem;
  }

  private static String emptyIfNull(String str) {
    return str != null ? str : "";
  }

  public ProblemException(
      URI type,
      String title,
      int status,
      String detail,
      URI instance,
      Map<String, Object> extensions,
      Throwable cause) {
    this(constructProblem(type, title, status, detail, instance, extensions), cause);
  }

  public ProblemException(
      URI type, String title, int status, String detail, URI instance, Throwable cause) {
    this(type, title, status, detail, instance, null, cause);
  }

  public ProblemException(URI type, String title, int status, String detail, Throwable cause) {
    this(type, title, status, detail, null, null, cause);
  }

  public ProblemException(URI type, String title, int status, Throwable cause) {
    this(type, title, status, null, null, null, cause);
  }

  public ProblemException(Problem problem, Throwable cause) {
    super(emptyIfNull(problem.getDetail()), cause);
    this.problem = problem;
  }

  public Problem getProblem() {
    return problem;
  }
}
