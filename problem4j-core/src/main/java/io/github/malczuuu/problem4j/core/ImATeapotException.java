package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class ImATeapotException extends ProblemException {

  private static final int STATUS_CODE = 418;
  private static final String REASON_PHRASE = "I'm a teapot";

  public ImATeapotException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public ImATeapotException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public ImATeapotException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public ImATeapotException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public ImATeapotException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public ImATeapotException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public ImATeapotException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public ImATeapotException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public ImATeapotException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public ImATeapotException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
