package pl.malczuuu.problem4j.core;

import java.net.URI;

public class UnprocessableEntityException extends ProblemException {

  private static final int STATUS_CODE = 422;
  private static final String REASON_PHRASE = "Unprocessable entity";

  public UnprocessableEntityException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public UnprocessableEntityException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public UnprocessableEntityException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public UnprocessableEntityException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public UnprocessableEntityException(
      String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public UnprocessableEntityException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public UnprocessableEntityException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public UnprocessableEntityException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public UnprocessableEntityException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public UnprocessableEntityException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
