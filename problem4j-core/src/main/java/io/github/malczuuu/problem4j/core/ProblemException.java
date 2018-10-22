package io.github.malczuuu.problem4j.core;

import java.net.URI;
import java.util.HashMap;

public class ProblemException extends RuntimeException {

  private final Problem problem;

  private ProblemException(Problem problem) {
    super(
        problem.getTitle()
            + (stringNotEmpty(problem.getDetail()) ? " [" + problem.getDetail() + "]" : ""));
    this.problem = problem;
  }

  private static boolean stringNotEmpty(String str) {
    return str != null && !str.isEmpty();
  }

  private ProblemException(Problem problem, Throwable cause) {
    super(
        problem.getTitle()
            + (stringNotEmpty(problem.getDetail()) ? " [" + problem.getDetail() + "]" : ""),
        cause);
    this.problem = problem;
  }

  public ProblemException(String title) {
    this(new Problem(Problem.BLANK_TYPE, title, 0, "", null, new HashMap<>()));
  }

  public ProblemException(String title, Throwable cause) {
    this(new Problem(Problem.BLANK_TYPE, title, 0, "", null, new HashMap<>()), cause);
  }

  public ProblemException(String title, String detail) {
    this(new Problem(Problem.BLANK_TYPE, title, 0, detail, null, new HashMap<>()));
  }

  public ProblemException(String title, String detail, Throwable cause) {
    this(new Problem(Problem.BLANK_TYPE, title, 0, detail, null, new HashMap<>()), cause);
  }

  public ProblemException(String title, int status) {
    this(new Problem(Problem.BLANK_TYPE, title, status, "", null, new HashMap<>()));
  }

  public ProblemException(String title, int status, Throwable cause) {
    this(new Problem(Problem.BLANK_TYPE, title, status, "", null, new HashMap<>()), cause);
  }

  public ProblemException(String title, int status, String detail) {
    this(new Problem(Problem.BLANK_TYPE, title, status, detail, null, new HashMap<>()));
  }

  public ProblemException(String title, int status, String detail, Throwable cause) {
    this(new Problem(Problem.BLANK_TYPE, title, status, detail, null, new HashMap<>()), cause);
  }

  public ProblemException(
      URI type,
      String title,
      int status,
      String detail,
      URI instance,
      Problem.Extension... extensions) {
    this(new Problem(type, title, status, detail, instance, extensions));
  }

  public ProblemException(
      URI type,
      String title,
      int status,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    this(new Problem(type, title, status, detail, instance, extensions), cause);
  }

  public ProblemException(
      String title, int status, String detail, Problem.Extension... extensions) {
    this(new Problem(Problem.BLANK_TYPE, title, status, detail, null, extensions));
  }

  public ProblemException(
      String title, int status, String detail, Throwable cause, Problem.Extension... extensions) {
    this(new Problem(Problem.BLANK_TYPE, title, status, detail, null, extensions), cause);
  }

  public ProblemException(
      URI type, String title, int status, String detail, Problem.Extension... extensions) {
    this(new Problem(type, title, status, detail, null, extensions));
  }

  public ProblemException(
      URI type,
      String title,
      int status,
      String detail,
      Throwable cause,
      Problem.Extension... extensions) {
    this(new Problem(type, title, status, detail, null, extensions), cause);
  }

  public Problem getProblem() {
    return problem;
  }
}
