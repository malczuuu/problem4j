package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class NotImplementedException extends ProblemException {

  private static final int STATUS_CODE = 501;
  private static final String REASON_PHRASE = "Not implemented";

  public NotImplementedException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public NotImplementedException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public NotImplementedException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public NotImplementedException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public NotImplementedException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public NotImplementedException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public NotImplementedException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public NotImplementedException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public NotImplementedException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public NotImplementedException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
