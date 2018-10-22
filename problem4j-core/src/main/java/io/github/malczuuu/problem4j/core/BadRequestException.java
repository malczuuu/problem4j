package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class BadRequestException extends ProblemException {

  private static final int STATUS_CODE = 400;
  private static final String REASON_PHRASE = "Bad request";

  public BadRequestException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public BadRequestException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public BadRequestException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public BadRequestException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public BadRequestException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public BadRequestException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public BadRequestException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public BadRequestException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public BadRequestException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
