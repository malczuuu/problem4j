package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class PreconditionFailedException extends ProblemException {

  private static final int STATUS_CODE = 412;
  private static final String REASON_PHRASE = "Precondition failed";

  public PreconditionFailedException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public PreconditionFailedException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public PreconditionFailedException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public PreconditionFailedException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public PreconditionFailedException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public PreconditionFailedException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public PreconditionFailedException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public PreconditionFailedException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public PreconditionFailedException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public PreconditionFailedException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
