package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class ForbiddenException extends ProblemException {

  private static final int STATUS_CODE = 403;
  private static final String REASON_PHRASE = "Forbidden";

  public ForbiddenException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public ForbiddenException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public ForbiddenException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public ForbiddenException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public ForbiddenException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public ForbiddenException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public ForbiddenException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public ForbiddenException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public ForbiddenException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public ForbiddenException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
