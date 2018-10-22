package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class UnauthorizedException extends ProblemException {

  private static final int STATUS_CODE = 401;
  private static final String REASON_PHRASE = "Unauthorized";

  public UnauthorizedException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public UnauthorizedException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public UnauthorizedException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public UnauthorizedException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public UnauthorizedException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public UnauthorizedException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public UnauthorizedException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public UnauthorizedException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public UnauthorizedException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
