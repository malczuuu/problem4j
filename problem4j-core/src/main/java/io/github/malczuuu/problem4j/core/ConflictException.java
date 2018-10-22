package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class ConflictException extends ProblemException {

  private static final int STATUS_CODE = 409;
  private static final String REASON_PHRASE = "Conflict";

  public ConflictException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public ConflictException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public ConflictException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public ConflictException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public ConflictException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public ConflictException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public ConflictException(URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public ConflictException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public ConflictException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public ConflictException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
