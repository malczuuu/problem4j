package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class MethodNotAllowedException extends ProblemException {

  private static final int STATUS_CODE = 405;
  private static final String REASON_PHRASE = "Method not allowed";

  public MethodNotAllowedException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public MethodNotAllowedException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public MethodNotAllowedException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public MethodNotAllowedException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public MethodNotAllowedException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public MethodNotAllowedException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public MethodNotAllowedException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public MethodNotAllowedException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public MethodNotAllowedException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public MethodNotAllowedException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
