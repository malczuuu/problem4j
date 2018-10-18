package pl.malczuuu.problem4j.core;

import java.net.URI;

public class BadGatewayException extends ProblemException {

  private static final int STATUS_CODE = 502;
  private static final String REASON_PHRASE = "Bad gateway";

  public BadGatewayException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public BadGatewayException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public BadGatewayException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public BadGatewayException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public BadGatewayException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public BadGatewayException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public BadGatewayException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public BadGatewayException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public BadGatewayException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public BadGatewayException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
