package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class InternalServerErrorException extends ProblemException {

  private static final int STATUS_CODE = 500;
  private static final String REASON_PHRASE = "Internal server error";

  public InternalServerErrorException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public InternalServerErrorException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public InternalServerErrorException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public InternalServerErrorException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public InternalServerErrorException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public InternalServerErrorException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public InternalServerErrorException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public InternalServerErrorException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public InternalServerErrorException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public InternalServerErrorException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
